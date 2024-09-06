/**
 * @Time: 2024/8/30 14:19
 * @Author: guoxun
 * @File: UserDetailsServiceImpl
 * @Description:
 */

package com.pipi.xojuserservice.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.pipi.xojuserservice.mapper.UserMapper;
import com.pipi.xojuserservice.pojo.domain.LoginUser;
import com.pipi.xojuserservice.pojo.domain.User;
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
    UserMapper userMapper;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getEmail, email));
        if (user == null){
            throw new BadCredentialsException("用户名或密码错误");
        }
        LoginUser loginUser = new LoginUser();
        BeanUtils.copyProperties(user, loginUser);
        return loginUser;
    }
}
