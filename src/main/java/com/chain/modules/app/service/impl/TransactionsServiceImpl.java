package com.chain.modules.app.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.chain.common.utils.DateUtils;
import com.chain.common.utils.HttpUtils;
import com.chain.common.utils.JsonUtil;
import com.chain.config.CommonConfig;
import com.chain.modules.app.dao.AccountsMapper;
import com.chain.modules.app.dao.TransactionsIndexMapper;
import com.chain.modules.app.dao.TransactionsMapper;
import com.chain.modules.app.entity.Accounts;
import com.chain.modules.app.entity.Transactions;
import com.chain.modules.app.entity.TransactionsIndex;
import com.chain.modules.app.rocksDB.RocksJavaUtil;
import com.chain.modules.app.service.TransactionsService;
import com.google.gson.reflect.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author: zz
 * @Description:
 * @Date: 下午 3:43 2019/1/22 0022
 * @Modified By
 */
@Service("transactionsService")
public class TransactionsServiceImpl implements TransactionsService {
    private Logger log = LoggerFactory.getLogger(getClass());


    @Resource
    private TransactionsIndexMapper transactionsIndexMapper;

    @Resource
    private TransactionsMapper transactionsMapper;

    @Resource
    private AccountsMapper accountsMapper;

    /**
     * 获取交易数历史据
     */
    @Override
    @Transactional
    public void requestTransactions() {
        TransactionsIndex index = transactionsIndexMapper.selectAll();
        Integer tableIndex = 0;
        Integer offset = 0;
        String url = CommonConfig.getNodeUrl() + CommonConfig.getNodeTransaction();
        HashMap<String,String> params = new HashMap<>();

        if(index == null){
            index = new TransactionsIndex.Builder()
                    .tableindex(tableIndex)
                    .offsets(offset)
                    .createTime(new Date())
                    .id(1L)
                    .build();
            transactionsIndexMapper.insertSelective(index);

        }else {
            tableIndex = index.getTableindex();
            offset = index.getOffsets();
        }

        params.put("tableIndex",tableIndex + "");
        params.put("offset",offset + "");
        try {
            String responseBody = HttpUtils.httpPost(url,params);
            JSONObject result = JSON.parseObject(responseBody);
            int code = result.getInteger("code");
            if(code == 200) {
                JSONObject data = result.getJSONObject("data");
                tableIndex = data.getInteger("tableIndex");
                offset = data.getInteger("offset");

                JSONArray list = data.getJSONArray("list");
                RocksJavaUtil rocks  = new RocksJavaUtil(CommonConfig.DBNUMBER);

                byte[] arr = rocks.get(CommonConfig.ACCOUNTNUMBER);

                Set<String> accounts = new HashSet<>();

                for(int i = 0 ; i < list.size(); i++){
                    JSONObject trans = list.getJSONObject(i);
                    String fromaddress = trans.getString("fromAddress");
                    String toaddress = trans.getString("toAddress");

                    Transactions transactions = new Transactions.Builder()
                            .ehash(trans.getString("eHash"))
                            .hash(trans.getString("hash"))
                            .fromaddress(fromaddress)
                            .toaddress(toaddress)
                            .id(trans.getBigDecimal("id"))
                            .isvalid(trans.getBoolean("isValid")?1L:0L)
                            .snapshot(trans.getString("snapshot"))
                            .type(trans.getString("type"))
                            .updatetime(trans.getLong("updateTime"))
                            .build();
                    transactionsMapper.insertSelective(transactions);
                    rocks.put(trans.getString("hash"),trans.toJSONString());

                    //统计地址
                    if(arr != null) {
                        accounts = JsonUtil.fromJson(new String(arr),new TypeToken<HashSet<String>>(){}.getType());
                        accounts.add(fromaddress);
                        accounts.add(toaddress);
                    }else {
                        accounts.add(fromaddress);
                        accounts.add(toaddress);
                    }
                }
                //更新索引
                index.setOffsets(offset);
                index.setTableindex(tableIndex);
                transactionsIndexMapper.updateByPrimaryKey(index);


                rocks.put(CommonConfig.ACCOUNTNUMBER,JsonUtil.toJson(accounts));
                Date date = DateUtils.stringToDate(DateUtils.format(new Date(), "yyyy-MM-dd"),"yyyy-MM-dd");
                Accounts accs = accountsMapper.selectByDate(date);
                if(accs != null) {
                    accs.setUpdateTime(new Date());
                    accs.setNumber(accounts.size()+"");
                    accountsMapper.updateByPrimaryKeySelective(accs);
                }else {
                    accs = new Accounts.Builder()
                            .number(accounts.size() + "")
                            .createTime(date)
                            .build();
                    accountsMapper.insertSelective(accs);
                }


            }else {
                log.error("coode is not 200");
            }


        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            log.error("getMessages error ~!" ,e);
        } catch (KeyStoreException e) {
            e.printStackTrace();
            log.error("getMessages error ~!" ,e);
        } catch (KeyManagementException e) {
            e.printStackTrace();
            log.error("getMessages error ~!" ,e);
        } catch (IOException e) {
            e.printStackTrace();
            log.error("getMessages error ~!" ,e);
        }


    }

    @Override
    public Transactions getTransactionByHash(String hash) {

        return transactionsMapper.selectByHash(hash);
    }
}
