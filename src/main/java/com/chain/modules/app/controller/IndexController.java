package com.chain.modules.app.controller;

import com.chain.common.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: zz
 * @Description:  1主页信息查询
 * @Date: 下午 4:21 2019/1/11 0011
 * @Modified By
 */
@RestController
@RequestMapping("/v1/index")
@Api("1主页信息查询")
public class IndexController {




    @PostMapping("/search")
    @ApiOperation("搜索栏目")
    public R serch() {



        return new R();
    }
}
