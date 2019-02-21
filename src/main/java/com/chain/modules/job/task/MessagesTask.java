package com.chain.modules.job.task;

import com.chain.modules.app.service.MessagesService;
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

@Component("messagesTask")
public class MessagesTask {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private MessagesService messagesService;


    //拉取账户信息
    public void getMessages() {
        logger.info("定时任务开始 -->开始拉取节点信息~!");
        messagesService.getMessages();
        logger.info("定时任务开始 -->拉取节点信息结束~!");
    }

    /**
     * 统计账户信息
     */
    public void countMessages() {
        logger.info("定时任务开始 -->开始拉取节点信息~!");
        logger.info("定时任务开始 -->拉取节点信息结束~!");
    }

}
