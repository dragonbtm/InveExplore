package com.chain.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chain.common.utils.HttpUtils;
import org.junit.Test;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

/**
 * @Author: zz
 * @Description:
 * @Date: 下午 2:09 2019/1/22 0022
 * @Modified By
 */
public class MessagesService {

    @Test
    public void getmessages() throws ClassNotFoundException, KeyManagementException, NoSuchAlgorithmException, KeyStoreException, IOException {
        String url = "http://18.191.18.104:31003/v1/getmessageInfo";
        String body = HttpUtils.httpPost(url,new HashMap<>());
        JSONObject result = JSON.parseObject(body);
        int code = result.getInteger("code");
        JSONObject data = result.getJSONObject("data");
        String runTime = data.getString("runTime");
        String shardNumber = data.getString("shardNumber");
        String tps = data.getString("tps");
        int userCount = data.getInteger("userCount");
        System.out.println(body);
    }

}
