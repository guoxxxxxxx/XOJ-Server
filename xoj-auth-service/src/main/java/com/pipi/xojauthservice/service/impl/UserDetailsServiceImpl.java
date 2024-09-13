/**
 * @Time: 2024/8/30 14:19
 * @Author: guoxun
 * @File: UserDetailsServiceImpl
 * @Description:
 */

package com.pipi.xojauthservice.service.impl;


import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.pipi.xojauthservice.feign.UserFeignClient;
import com.pipi.xojauthservice.mapper.AuthMapper;
import com.pipi.xojauthservice.pojo.domain.AuthInfo;
import com.pipi.xojauthservice.pojo.domain.LoginUser;
import com.pipi.xojcommon.common.CommonResult;
import org.springframework.beans.BeanUtils;
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

    @Autowired
    AuthMapper authMapper;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        CommonResult commonResult = userFeignClient.getUserJsonByEmail(email);
        JSONObject jsonObject = JSON.parseObject((String) commonResult.getData(), JSONObject.class);
        String uid = jsonObject.getString("id");
        AuthInfo authInfo = authMapper.selectOne(new LambdaQueryWrapper<AuthInfo>().eq(AuthInfo::getUid, uid));
        String userInfoJson = (String) commonResult.getData();
        if (userInfoJson == null){
            throw new BadCredentialsException("用户名或密码错误");
        }
        LoginUser loginUser = JSON.parseObject(userInfoJson, LoginUser.class);
        BeanUtils.copyProperties(authInfo, loginUser);
        return loginUser;
    }
}
