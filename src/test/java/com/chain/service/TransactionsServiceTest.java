package com.chain.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.chain.common.utils.HttpUtils;
import com.chain.modules.app.entity.Transactions;
import org.junit.Test;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

/**
 * @Author: zz
 * @Description:
 * @Date: 下午 4:27 2019/1/22 0022
 * @Modified By
 */
public class TransactionsServiceTest {

    @Test
    public void transactionsTest() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        String url = "http://18.191.18.104:31003/v1/getmessagelist";
        HashMap<String,String> params = new HashMap<>();
        params.put("tableIndex","0");
        params.put("offset","0");

        String responseBody = HttpUtils.httpPost(url,params);
        JSONObject result = JSON.parseObject(responseBody);
        JSONObject data = result.getJSONObject("data");
        Integer tableIndex = data.getInteger("tableIndex");
        Integer offset = data.getInteger("offset");

        JSONArray list = data.getJSONArray("list");
        JSONObject trans = list.getJSONObject(0);
        System.out.println(trans);


    }
}
