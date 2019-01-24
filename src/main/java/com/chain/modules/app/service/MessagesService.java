package com.chain.modules.app.service;


import java.util.Map;

import com.chain.common.utils.R;

/**
 * @Author: zz
 * @Description:
 * @Date: 上午 11:46 2019/1/22 0022
 * @Modified By
 */
public interface MessagesService {

    void getMessages();

    Map<String,Object> selectByNull();

    R getGraphdDtas();

    R getTransactionInfoByHash(String hash);

    R getTransactionsByAddress(Map<String,Object> map);
}
