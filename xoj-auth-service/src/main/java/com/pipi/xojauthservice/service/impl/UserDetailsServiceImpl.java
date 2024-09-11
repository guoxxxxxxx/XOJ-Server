/**
 * @Time: 2024/8/30 14:19
 * @Author: guoxun
 * @File: UserDetailsServiceImpl
 * @Description:
 */

package com.pipi.xojauthservice.service.impl;


import com.alibaba.fastjson2.JSON;
import com.pipi.xojauthservice.feign.UserFeignClient;
import com.pipi.xojauthservice.pojo.domain.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserFeignClient userFeignClient;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        String userInfoJson = userFeignClient.getUserJsonByEmail(email);
        if (userInfoJson == null){
            throw new BadCredentialsException("用户名或密码错误");
        }
        return JSON.parseObject(userInfoJson, LoginUser.class);
    }
}
