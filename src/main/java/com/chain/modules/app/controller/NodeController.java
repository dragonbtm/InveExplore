package com.chain.modules.app.controller;

import com.chain.common.utils.R;
import com.chain.modules.app.service.MessagesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

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



    @GetMapping("/preview")
    @ApiOperation("全网预览")
    public R preview() {

     return R.ok(messagesService.selectByNull());

    }


    @PostMapping("/messageslist")
    @ApiOperation("消息列表")
    public R messagesList() {

        return new R();
    }


    @PostMapping("/messagesinfo")
    @ApiOperation("消息详细信息")
    public R messagesinfo() {

        return new R();
    }



    @PostMapping("/graphdatas")
    @ApiOperation("图表数据")
    public R graphdatas() {
        return messagesService.getGraphdDtas();
    }


}
