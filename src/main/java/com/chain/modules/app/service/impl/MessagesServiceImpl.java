package com.chain.modules.app.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.chain.common.utils.DateUtils;
import com.chain.common.utils.HttpUtils;
import com.chain.common.utils.R;
import com.chain.config.CommonConfig;
import com.chain.modules.app.dao.AccountsMapper;
import com.chain.modules.app.dao.MessagesMapper;
import com.chain.modules.app.dao.TransactionsMapper;
import com.chain.modules.app.entity.Accounts;
import com.chain.modules.app.entity.Messages;
import com.chain.modules.app.entity.Transactions;
import com.chain.modules.app.service.AccountsService;
import com.chain.modules.app.service.MessagesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

/**
 * @Author: zz
 * @Description:
 * @Date: 上午 11:47 2019/1/22 0022
 * @Modified By
 */
@Service("messagesService")
public class MessagesServiceImpl implements MessagesService {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    private MessagesMapper messagesMapper;
    @Resource
    private TransactionsMapper transactionsMapper;
    @Resource
    private AccountsMapper accountsMapper;



    /**
     * 从局部全节点拉取历史信息
     */
    @Override
    public void getMessages() {
        String url = CommonConfig.getNodeUrl() + CommonConfig.getNodeMessages();
        Date date = DateUtils.stringToDate(DateUtils.format(new Date(), "yyyy-MM-dd"),"yyyy-MM-dd");
        try {
            String responseBody = HttpUtils.httpPost(url,new HashMap<>());
            JSONObject result = JSON.parseObject(responseBody);
            int code = result.getInteger("code");
            if(code == 200) {
                JSONObject data = result.getJSONObject("data");
                String runTime = data.getString("runTime");
                String shardNumber = data.getString("shardNumber");
                String tps = data.getString("tps");
                Long userCount = data.getLong("userCount");

                Messages messages = messagesMapper.selectByCreateTime(date);

                if(messages != null) {
                    messages = new Messages.Builder()
                            .runtime(runTime)
                            .shardnumber(shardNumber)
                            .tps(tps)
                            .usercount(userCount)
                            .updateTime(new Date())
                            .build();
                    messagesMapper.updateByPrimaryKeySelective(messages);
                }else {
                    messages = new Messages.Builder()
                            .runtime(runTime)
                            .shardnumber(shardNumber)
                            .tps(tps)
                            .usercount(userCount)
                            .createTime(date)
                            .build();
                    messagesMapper.insertSelective(messages);
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
    public  Map<String,Object> selectByNull() {

        Date date = DateUtils.stringToDate(DateUtils.format(new Date(), "yyyy-MM-dd"),"yyyy-MM-dd");
        Messages messages = messagesMapper.selectByCreateTime(date);
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("messages",messages);
        int messageTotal=transactionsMapper.selectMessageTotal();
        map.put("messageTotal",messageTotal);
        Accounts accounts =accountsMapper.selectByDate(date);
        map.put("accountsTotal",accounts.getNumber());
        return map;


    }

    /**
     *
     * @return
     */
    @Override
    public R getGraphdDtas() {
        //14天消息数量
        List<Integer> messages = messagesMapper.selectTrans();
        int msgSiz = 14 - messages.size();
        if(msgSiz > 0) {
            for (int i = 0 ; i < msgSiz ; i++)
                messages.add(0,0);
        }
        //14天用户数量
        List<Integer> accounts = accountsMapper.selectAccs();
        msgSiz = 14 - accounts.size();
        if(msgSiz > 0) {
            for (int i = 0 ; i < msgSiz ; i++)
                accounts.add(0,0);
        }


        return R.ok().put("messages",messages).put("accounts",accounts);
    }
}
