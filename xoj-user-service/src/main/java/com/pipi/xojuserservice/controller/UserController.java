/**
 * @Time: 2024/8/30 15:10
 * @Author: guoxun
 * @File: UserController
 * @Description:
 */

package com.pipi.xojuserservice.controller;

import com.pipi.xojcommon.aop.annotation.Logger;
import com.pipi.xojcommon.common.CommonResult;
import com.pipi.xojuserservice.pojo.domain.UserInfo;
import com.pipi.xojuserservice.pojo.dto.UserRegisterDTO;
import com.pipi.xojuserservice.service.UserService;
import io.swagger.annotations.ApiOperation;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    @Logger("用户注册")
    @ApiOperation("用户注册")
    @PostMapping("/ua/register")
    public CommonResult register(@RequestBody UserRegisterDTO userRegisterDTO, HttpServletRequest request){
        return userService.register(userRegisterDTO, request);
    }


    @Logger("用户注册时发送随机验证码")
    @ApiOperation("用户注册时发送随机验证码")
    @GetMapping("/registerSendAuthCode")
    public CommonResult sendAuthCode(@RequestParam String email){
        return userService.registerSendAuthCode(email);
    }


    @Logger("根据id查询用户信息")
    @ApiOperation("根据id查询用户信息")
    @GetMapping ("/{id}")
    public CommonResult getUserById(@PathVariable Integer id){
        UserInfo userInfo = userService.queryById(id);
        return new CommonResult().success().message("查询数据成功").data(userInfo);
    }


    @Logger("根据email查询用户信息")
    @ApiOperation("根据email查询用户信息")
    @GetMapping("/getUserJsonByEmail")
    public String getUserJsonByEmail(@RequestParam String email){
        return userService.queryByEmail(email);
    }
}
