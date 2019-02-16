package com.chain.modules.app.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.chain.common.utils.*;
import com.chain.config.CommonConfig;
import com.chain.config.CommonDataDefine;
import com.chain.modules.app.dao.AccountsMapper;
import com.chain.modules.app.dao.TransactionsIndexMapper;
import com.chain.modules.app.dao.TransactionsMapper;
import com.chain.modules.app.entity.Accounts;
import com.chain.modules.app.entity.Transactions;
import com.chain.modules.app.entity.TransactionsIndex;
import com.chain.modules.app.rocksDB.RocksJavaUtil;
import com.chain.modules.app.service.TransactionsService;
import com.chain.modules.app.utils.NodeUtils;
import com.google.gson.reflect.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.io.IOException;
import java.math.BigDecimal;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * @Author: zz
 * @Description:
 * @Date: 下午 3:43 2019/1/22 0022
 * @Modified By
 */
@Service("transactionsService")
public class TransactionsServiceImpl extends ServiceImpl<TransactionsMapper,Transactions> implements TransactionsService {
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

        String url =  CommonDataDefine.localfullnodeUrl;
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
            if (StringUtils.isNull(url)) {
                url = NodeUtils.getLocalfullnode();
            }

            url += CommonConfig.getVersion() +CommonConfig.getNodeTransaction();

            log.info("请求历史数据->" + url);
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
                if(arr != null) {
                    accounts = JsonUtil.fromJson(new String(arr),new TypeToken<HashSet<String>>(){}.getType());
                }

                for(int i = 0 ; i < (list != null?list.size():0) ; i++){
                    JSONObject trans = list.getJSONObject(i);
                    String fromaddress = trans.getString("fromAddress");
                    String toaddress = trans.getString("toAddress");
                    BigDecimal amount = trans.getBigDecimal("amount");
                    BigDecimal fee = trans.getBigDecimal("fee");

                    Transactions transactions = new Transactions.Builder()
                            .ehash(trans.getString("eHash"))
                            .hash(trans.getString("hash"))
                            .fromaddress(fromaddress)
                            .toaddress(toaddress)
                            .id(trans.getBigInteger("id"))
                            .isvalid(trans.getBoolean("isValid")?1L:0L)
                            .snapshot(trans.getString("snapshot"))
                            .type(trans.getString("type"))
                            .updatetime(trans.getLong("updateTime"))
                            .build();
                    transactionsMapper.insertSelective(transactions);
                    rocks.put(trans.getString("hash"),trans.toJSONString());

                    //统计地址
                    accounts.add(fromaddress);
                    accounts.add(toaddress);

                    //统计账户总金额
                    if(!transactions.getType().equals("3")) {
                        byte[] fromBytes = rocks.get(fromaddress);
                        byte[] toBytes = rocks.get(toaddress);

                        BigDecimal fromAddressAmount = BigDecimal.ZERO;
                        BigDecimal toAddressAmount = BigDecimal.ZERO;

                        if(fromBytes != null) {
                            fromAddressAmount = new BigDecimal(new String(fromBytes));
                        }
                        if(toBytes != null) {
                            toAddressAmount = new BigDecimal(new String(fromBytes));
                        }



                        if(amount != null) {
                            fromAddressAmount = fromAddressAmount.subtract(amount);
                            toAddressAmount = toAddressAmount.add(amount);
                        }
                        if(fee != null) {
                            fromAddressAmount = fromAddressAmount.subtract(fee);
                        }

                        rocks.put(fromaddress,fromAddressAmount.toString());
                        rocks.put(toaddress,toAddressAmount.toString());
                    }



                }
                //更新索引
                index.setOffsets(offset);
                index.setTableindex(tableIndex);
                transactionsIndexMapper.updateByPrimaryKey(index);

                //将地址 集合 放入缓存
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
    public R getList(@RequestParam Map<String, Object> map) {
        String typeId = (String) map.get("type");
        EntityWrapper<Transactions> et = new EntityWrapper<Transactions>();
        if(!StringUtils.isNull(typeId)){
            et.eq("type",typeId);
        }
        List<String> orderBy = new ArrayList<>();
        orderBy.add("updateTime");
        et.orderDesc(orderBy);
        Page page = this.selectPage(
                new Query(map).getPage(),
                et
        );

        RocksJavaUtil rocksDb = new RocksJavaUtil(CommonConfig.DBNUMBER);
        List list = new ArrayList();
        for (Object tran : page.getRecords()) {
            String data = new String(rocksDb.get(((Transactions)tran).getHash()));
            try {
                list.add(JSON.parseObject(data));
            } catch (Exception e) {
                e.printStackTrace();
                log.error("json数据处理异常",e);
                return R.error("json数据处理异常");
            }
        }
        page.setRecords(list);
        return R.ok().put("page",new PageUtils(page));
    }

    @Override
    public Transactions getTransactionByHash(String hash) {

        return transactionsMapper.selectByHash(hash);
    }

    @Override
    public List<Object> selectByAddress(Map<String,Object> params) {
        return transactionsMapper.selectByAddress(params);
    }

    @Override
    public int selectMessageTotal() {
        return transactionsMapper.selectMessageTotal();
    }
}
