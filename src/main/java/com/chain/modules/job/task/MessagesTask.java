package com.chain.modules.job.task;

import com.chain.modules.app.service.MessagesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

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
        try {
            messagesService.getMessages();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }
        logger.info("定时任务开始 -->拉取节点信息结束~!");
    }


}
