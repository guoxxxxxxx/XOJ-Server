/**
 * @Time: 2024/9/11 14:39
 * @Author: guoxun
 * @File: AuthController
 * @Description:
 */

package com.pipi.xojauthservice.controller;


import com.pipi.xojauthservice.pojo.dto.ChangePasswordDTO;
import com.pipi.xojauthservice.pojo.dto.LoginDTO;
import com.pipi.xojauthservice.pojo.dto.RetrievePasswordDTO;
import com.pipi.xojauthservice.service.AuthService;
import com.pipi.xojcommon.aop.annotation.Logger;
import com.pipi.xojcommon.common.CommonResult;
import com.pipi.xojcommon.common.CustomHttpStatus;
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
    @PostMapping("/login")
    public CommonResult login(@RequestBody LoginDTO loginDTO, HttpServletRequest request){
        Map<String, String> token = authService.login(loginDTO, request);
        return new CommonResult().success().data(token).message("登录成功");
    }


    @Logger("用户注册保存密码")
    @ApiOperation("用户注册保存密码")
    @PostMapping("/savePassword")
    public CommonResult savePassword(@RequestBody String infoJson){
        Boolean status = authService.savePassword(infoJson);
        return new CommonResult().success().message("保存成功").data(status);
    }


    @Logger("修改密码")
    @ApiOperation("修改密码")
    @PutMapping("/changePassword")
    public CommonResult changePassword(@RequestBody ChangePasswordDTO dto){
        Boolean result = authService.changePassword(dto);
        if (result)
            return new CommonResult().success().message("密码更新成功").data(result);
        else
            return new CommonResult().fail().message("密码更新失败").data(result);
    }


    @Logger("找回密码验证码")
    @ApiOperation("找回密码验证码")
    @GetMapping("/getRetrievePasswordAuthCode")
    public CommonResult getRetrievePasswordAuthCode(String email) throws Exception {
        if (email.isEmpty())
            return new CommonResult().code(CustomHttpStatus.EMAIL_FORMAT_ERROR);
        else {
            authService.getRetrievePasswordAuthCode(email);
        }
        return new CommonResult().success().message("发送成功");
    }


    @Logger("找回密码")
    @ApiOperation("找回密码")
    @PutMapping("/retrievePassword")
    public CommonResult retrievePassword(@RequestBody RetrievePasswordDTO dto){
        authService.retrievePassword(dto);
        return new CommonResult().success().message("修改密码成功");
    }
}
