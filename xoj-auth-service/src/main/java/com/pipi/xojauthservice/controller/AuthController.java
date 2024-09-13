/**
 * @Time: 2024/9/11 14:39
 * @Author: guoxun
 * @File: AuthController
 * @Description:
 */

package com.pipi.xojauthservice.controller;


import com.pipi.xojauthservice.pojo.dto.LoginDTO;
import com.pipi.xojauthservice.service.AuthService;
import com.pipi.xojcommon.aop.annotation.Logger;
import com.pipi.xojcommon.common.CommonResult;
import io.swagger.annotations.ApiOperation;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {


    @Autowired
    private AuthService authService;


    @Logger("用户登录")
    @ApiOperation("用户登录")
    @PostMapping("/ua/login")
    public CommonResult login(@RequestBody LoginDTO loginDTO, HttpServletRequest request){
        Map<String, String> token = authService.login(loginDTO, request);
        return new CommonResult().success().data(token).message("登录成功");
    }


    @Logger("用户注册保存密码")
    @ApiOperation("用户注册保存密码")
    @PostMapping("/ua/savePassword")
    public CommonResult savePassword(@RequestBody String infoJson){
        Boolean status = authService.savePassword(infoJson);
        return new CommonResult().success().message("保存成功").data(status);
    }
}
