/**
 * @Time: 2024/9/11 14:40
 * @Author: guoxun
 * @File: AuthServiceImpl
 * @Description:
 */

package com.pipi.xojauthservice.service.impl;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pipi.xojauthservice.feign.UserFeignClient;
import com.pipi.xojauthservice.mapper.AuthMapper;
import com.pipi.xojauthservice.pojo.domain.AuthInfo;
import com.pipi.xojauthservice.pojo.domain.LoginUser;
import com.pipi.xojauthservice.pojo.dto.LoginDTO;
import com.pipi.xojauthservice.service.AuthService;
import com.pipi.xojcommon.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service
public class AuthServiceImpl extends ServiceImpl<AuthMapper, AuthInfo> implements AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserFeignClient userFeignClient;

    @Override
    public Map<String, String> login(LoginDTO loginDTO, HttpServletRequest request) {
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        String userJson = userFeignClient.getUserJsonByEmail(loginDTO.getEmail());
        LoginUser loginUser = JSON.parseObject(userJson, LoginUser.class);
        return Map.of("token", JwtUtils.createJWT(JSON.toJSONString(loginUser)));
    }


    @Override
    public Boolean savePassword(String infoJson) {
        AuthInfo authInfo = JSON.parseObject(infoJson, AuthInfo.class);
        int insert = baseMapper.insert(authInfo);
        return insert == 1;
    }
}
