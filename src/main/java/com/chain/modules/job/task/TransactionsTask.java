package com.chain.modules.job.task;

import com.chain.modules.app.service.AccountsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author: zz
 * @Description:
 * @Date: 下午 2:50 2019/1/15 0015
 * @Modified By
 */

@Component("transactionsTask")
public class TransactionsTask {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private AccountsService accountsService;


    //拉取账户信息
    public void getAccountNumber() {
        logger.info("定时任务开始 -->开始拉取交易信息~!");
        accountsService.getAccountsInfo();
        logger.info("定时任务开始 -->拉取交易信息结束~!");
    }


}
