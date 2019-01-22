package com.chain.modules.app.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chain.common.utils.DateUtils;
import com.chain.common.utils.HttpUtils;
import com.chain.config.CommonConfig;
import com.chain.modules.app.dao.MessagesMapper;
import com.chain.modules.app.entity.Messages;
import com.chain.modules.app.service.MessagesService;
import org.apache.ibatis.annotations.ResultMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;

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
}
