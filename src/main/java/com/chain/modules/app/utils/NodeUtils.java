package com.chain.modules.app.utils;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chain.common.utils.HttpUtils;
import com.chain.common.utils.StringUtils;
import com.chain.config.CommonConfig;
import com.chain.config.CommonDataDefine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

/**
 * @Author: zz
 * @Description:
 * @Date: 下午 5:28 2019/2/15 0015
 * @Modified By
 */
public class NodeUtils {
    private static Logger log = LoggerFactory.getLogger(NodeUtils.class);

    public static String getLocalfullnode() throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException, IOException {
        String url = null;
        String seedurl = CommonConfig.getNodeUrl() + CommonConfig.getVersion() + CommonConfig.getLocalfullnodes();
        log.info("请求局部全节点列表->" + seedurl);
        HashMap<String,String> seedparams = new HashMap<>();
        seedparams.put("pubkey","A78IhF6zjQIGzuzKwrjG9HEISz7/oAoEhyr7AnBr3RWn");
        JSONObject responseBody1 = JSON.parseObject(HttpUtils.httpPost(seedurl,seedparams));
        if(responseBody1.getInteger("code") == 200) {
            JSONObject localfullnode = responseBody1.getJSONArray("data").getJSONObject(0);
            url = "http://" + localfullnode.getString("ip") + ":" + localfullnode.getString("httpPort");
            CommonDataDefine.localfullnodeUrl = url;

        }else {
            log.info("请求局部全节点列表->" + "出现异常");
        }
        return url;
    }
}
