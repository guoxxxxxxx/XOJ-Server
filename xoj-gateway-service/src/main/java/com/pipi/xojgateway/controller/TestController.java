/**
 * @Time: 2024/9/5 15:43
 * @Author: guoxun
 * @File: TestController
 * @Description:
 */

package com.pipi.xojgateway.controller;

import com.pipi.xojcommon.common.CommonResult;
import com.pipi.xojgateway.feign.UserFeignClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/gateway")
public class TestController {

    @Autowired
    UserFeignClient userFeignClient;

    @GetMapping("/test")
    public CommonResult test(){
        CommonResult userById = userFeignClient.getUserById(1);
        return userById;
    }
}
