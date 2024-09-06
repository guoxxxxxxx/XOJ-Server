/**
 * @Time: 2024/8/30 15:10
 * @Author: guoxun
 * @File: UserController
 * @Description:
 */

package com.pipi.xojuserservice.controller;

import com.pipi.xojcommon.aop.annotation.Logger;
import com.pipi.xojcommon.common.CommonResult;
import com.pipi.xojuserservice.pojo.domain.User;
import com.pipi.xojuserservice.pojo.dto.LoginDTO;
import com.pipi.xojuserservice.pojo.dto.UserRegisterDTO;
import com.pipi.xojuserservice.service.UserService;
import io.swagger.annotations.ApiOperation;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Logger("用户注册")
    @ApiOperation("用户注册")
    @PostMapping("/register")
    public CommonResult register(@RequestBody UserRegisterDTO userRegisterDTO){
        return userService.register(userRegisterDTO);
    }


    @Logger("用户注册时发送随机验证码")
    @ApiOperation("用户注册时发送随机验证码")
    @GetMapping("/registerSendAuthCode")
    public CommonResult sendAuthCode(@RequestParam String email){
        return userService.registerSendAuthCode(email);
    }


    @Logger("用户登录")
    @ApiOperation("用户登录")
    @PostMapping("/login")
    public CommonResult login(@RequestBody LoginDTO loginDTO, HttpServletRequest request){
        Map<String, String> token = userService.login(loginDTO, request);
        return new CommonResult().success().data(token).message("登录成功");
    }


    @Logger("根据id查询用户信息")
    @ApiOperation("根据id查询用户信息")
    @GetMapping ("/{id}")
    public CommonResult getUserById(@PathVariable Integer id){
        User user = userService.queryById(id);
        return new CommonResult().success().message("查询数据成功").data(user);
    }
}
