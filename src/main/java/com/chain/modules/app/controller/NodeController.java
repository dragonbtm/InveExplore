package com.chain.modules.app.controller;

import com.chain.common.utils.R;
import com.chain.common.utils.StringUtils;
import com.chain.config.CommonDataDefine;
import com.chain.modules.app.service.MessagesService;
import com.chain.modules.app.service.TransactionsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.Map;

/**
 * @Author: zz
 * @Description: 3节点信息
 * @Date: 下午 4:22 2019/1/11 0011
 * @Modified By
 */
@RestController
@RequestMapping("/v1/node")
@Api("3节点信息")
public class NodeController {

    @Autowired
    private MessagesService messagesService;

    @Autowired
    private TransactionsService transactionsService;


    @PostMapping("/preview")
    @ApiOperation("全网预览")
    public R preview() {
        if (CommonDataDefine.previewNum == 1) {
            CommonDataDefine.previewNum++;
            try {
                messagesService.getMessages();
            } catch (Exception e) {
            }
        }
        return R.ok(messagesService.selectByNull());
    }


    @PostMapping("/messageslist")
    @ApiOperation("消息列表")
    public R messagesList(@RequestParam Map<String, Object> map) {
        Long oldTime = CommonDataDefine.transactionsTimestamp;
        Long newTime = Instant.now().getEpochSecond();
        if(newTime - oldTime > 30) {
            try {
                transactionsService.requestTransactions();
            } catch (Exception e) {
            }
            CommonDataDefine.transactionsTimestamp = Instant.now().getEpochSecond();
        }
//        transactionsService.requestTransactions();
        return transactionsService.getList(map);
    }


    @PostMapping("/messagesinfo")
    @ApiOperation("消息详细信息")
    public R messagesinfo(@RequestParam Map<String, Object> map) {
        String hash = (String) map.get("hash");
        String address = (String) map.get("address");
        if (!StringUtils.isNull(hash)) {
            return messagesService.getTransactionInfoByHash(hash);
        } else if (!StringUtils.isNull(address)) {
            return messagesService.getTransactionsByAddress(map);
        } else {
            return R.error("param is wrong~!");
        }
    }


    @PostMapping("/graphdatas")
    @ApiOperation("图表数据")
    public R graphdatas() {
        return messagesService.getGraphdDtas();
    }


}
