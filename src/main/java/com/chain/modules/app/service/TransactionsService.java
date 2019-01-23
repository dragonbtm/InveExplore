package com.chain.modules.app.service;

import com.chain.modules.app.entity.Transactions;

/**
 * @Author: zz
 * @Description: 交易数据
 * @Date: 下午 7:10 2019/1/15 0015
 * @Modified By
 */
public interface TransactionsService {
    void requestTransactions();

    Transactions getTransactionByHash(String hash);
}
