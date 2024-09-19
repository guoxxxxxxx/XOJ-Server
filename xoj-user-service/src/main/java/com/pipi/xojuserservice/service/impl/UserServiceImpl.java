/**
 * @Time: 2024/8/30 14:30
 * @Author: guoxun
 * @File: UserServiceImpl
 * @Description:
 */

package com.pipi.xojuserservice.service.impl;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pipi.xojcommon.common.CommonResult;
import com.pipi.xojcommon.common.CustomHttpStatus;
import com.pipi.xojcommon.constant.RedisNamespace;
import com.pipi.xojcommon.utils.JwtUtils;
import com.pipi.xojcommon.utils.MailUtils;
import com.pipi.xojcommon.utils.RandomAuthCodeUtil;
import com.pipi.xojuserservice.feign.AuthFeignClient;
import com.pipi.xojuserservice.mapper.UserMapper;
import com.pipi.xojuserservice.pojo.domain.UserInfo;
import com.pipi.xojuserservice.pojo.dto.UserRegisterDTO;
import com.pipi.xojuserservice.service.UserService;
import io.seata.spring.annotation.GlobalTransactional;
import jakarta.servlet.http.HttpServletRequest;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserInfo> implements UserService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private AuthFeignClient authFeignClient;


    @Override
    public Boolean checkEmailIsRegister(String email){
        return baseMapper.exists(new LambdaQueryWrapper<UserInfo>().eq(UserInfo::getEmail, email));
    }


    @Override
    @GlobalTransactional
    public CommonResult register(UserRegisterDTO userRegisterDTO, HttpServletRequest request) {
        if (userRegisterDTO.getAuthCode() == null || !userRegisterDTO.getAuthCode().equals(stringRedisTemplate
                .opsForValue().get(RedisNamespace.AUTH_CODE_REGISTER.getFullPathKey(userRegisterDTO.getEmail())))){
            return new CommonResult().code(CustomHttpStatus.AUTH_CODE_ERROR);
        }
        else {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String encodePassword = passwordEncoder.encode(userRegisterDTO.getPassword());
            userRegisterDTO.setPassword(encodePassword);
            UserInfo user = new UserInfo();
            BeanUtils.copyProperties(userRegisterDTO, user);
            user.setRegisterTime(new Date());
            user.setLastLoginIp(request.getRemoteAddr());
            if (checkEmailIsRegister(userRegisterDTO.getEmail()))
                return new CommonResult().code(CustomHttpStatus.EMAIL_ALREADY_REGISTER);
            if (baseMapper.insert(user) == 1){
                String token = JwtUtils.createJWT(JSON.toJSONString(user));
                HashMap<String, Object> infoMap = new HashMap<>();
                infoMap.put("uid", user.getId());
                infoMap.put("lastModifyIp", request.getRemoteAddr());
                infoMap.put("lastModifyTime", new Date());
                infoMap.put("password", encodePassword);
                infoMap.put("token", token);
                stringRedisTemplate.opsForValue().set(
                        RedisNamespace.REGISTER_TOKEN.getFullPathKey(String.valueOf(user.getId())), token);
                authFeignClient.savePassword(JSON.toJSONString(infoMap));
                stringRedisTemplate.delete(
                        RedisNamespace.AUTH_CODE_REGISTER.getFullPathKey(userRegisterDTO.getEmail()));
                stringRedisTemplate.delete(RedisNamespace.REGISTER_TOKEN.getFullPathKey(String.valueOf(user.getId())));
                return new CommonResult().message("注册成功").success().data("token", token);
            }
            else {
                return new CommonResult().code(CustomHttpStatus.REGISTER_FAIL).data("token", null);
            }
        }
    }


    @Override
    public CommonResult registerSendAuthCode(String email) {
        if (!MailUtils.checkEmailIsCorrect(email))
            return new CommonResult().code(CustomHttpStatus.EMAIL_FORMAT_ERROR);
        if (checkEmailIsRegister(email))
            return new CommonResult().code(CustomHttpStatus.EMAIL_ALREADY_REGISTER);
        if (StringUtils.hasLength(stringRedisTemplate.opsForValue()
                .get(RedisNamespace.AUTH_CODE_REGISTER.getFullPathKey(email)))){
            Long expire = stringRedisTemplate.getExpire(
                    RedisNamespace.AUTH_CODE_REGISTER.getFullPathKey(email), TimeUnit.MINUTES);
            if (expire != null && expire >= 9L)
                return new CommonResult().code(CustomHttpStatus.AUTH_CODE_SEND_FREQUENT);
        }
        String authCode = RandomAuthCodeUtil.getRandomAuthCode();
        try {
            MailUtils.sendRandomCode(authCode, email, "10");
            stringRedisTemplate.opsForValue().set(RedisNamespace.AUTH_CODE_REGISTER.getFullPathKey(email)
                    , authCode, 10, TimeUnit.MINUTES);
            return new CommonResult().success().message("验证码发送成功!");
        } catch (Exception e){
            log.error(e.toString());
            return new CommonResult().code(CustomHttpStatus.EMAIL_SERVER_ERROR);
        }
    }

    @Override
    public String queryByEmail(String email) {
        UserInfo userInfo = baseMapper.selectOne(new LambdaQueryWrapper<UserInfo>().eq(UserInfo::getEmail, email));
        return userInfo == null ? null : JSON.toJSONString(userInfo);
    }


    @Override
    public UserInfo queryById(Integer id) {
        System.out.println("running");
        return baseMapper.selectById(id);
    }
}
