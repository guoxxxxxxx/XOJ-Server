/**
 * @Time: 2024/9/11 14:40
 * @Author: guoxun
 * @File: AuthServiceImpl
 * @Description:
 */

package com.pipi.xojauthservice.service.impl;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pipi.xojauthservice.feign.UserFeignClient;
import com.pipi.xojauthservice.mapper.AuthMapper;
import com.pipi.xojauthservice.pojo.domain.AuthInfo;
import com.pipi.xojauthservice.pojo.domain.LoginUser;
import com.pipi.xojauthservice.pojo.dto.ChangePasswordDTO;
import com.pipi.xojauthservice.pojo.dto.LoginDTO;
import com.pipi.xojauthservice.pojo.dto.RetrievePasswordDTO;
import com.pipi.xojauthservice.service.AuthService;
import com.pipi.xojcommon.common.CommonException;
import com.pipi.xojcommon.common.CommonResult;
import com.pipi.xojcommon.common.CustomHttpStatus;
import com.pipi.xojcommon.constant.RedisNamespace;
import com.pipi.xojcommon.utils.JwtUtils;
import com.pipi.xojcommon.utils.MailUtils;
import com.pipi.xojcommon.utils.RandomAuthCodeUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.json.Json;
import javax.json.JsonObject;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;


@Service
public class AuthServiceImpl extends ServiceImpl<AuthMapper, AuthInfo> implements AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserFeignClient userFeignClient;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public Map<String, String> login(LoginDTO loginDTO, HttpServletRequest request) {
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        CommonResult resultJson = userFeignClient.getUserJsonByEmail(loginDTO.getEmail());
        String userJson = (String) resultJson.getData();
        JSONObject jsonObject = (JSONObject) JSON.parse(userJson);
        String uid = String.valueOf(jsonObject.get("id"));
        LoginUser loginUser = JSON.parseObject(userJson, LoginUser.class);
        AuthInfo authInfo = baseMapper.selectOne(new LambdaQueryWrapper<AuthInfo>().eq(AuthInfo::getUid, uid));
        BeanUtils.copyProperties(authInfo, loginUser);
        return Map.of("token", JwtUtils.createJWT(JSON.toJSONString(loginUser)));
    }


    @Override
    public Boolean savePassword(String infoJson) {
        AuthInfo authInfo = new AuthInfo();
        JSONObject jsonObject = JSON.parseObject(infoJson, JSONObject.class);
        String uid = jsonObject.getString("uid");
        String token = stringRedisTemplate.opsForValue().get(RedisNamespace.REGISTER_TOKEN.getFullPathKey(uid));
        if (token == null || jsonObject.get("token") == null || !token.equals(jsonObject.getString("token"))){
            if (token != null){
                stringRedisTemplate.delete(RedisNamespace.REGISTER_TOKEN.getFullPathKey(uid));
            }
            throw new RuntimeException("令牌不合法, 保存密码失败!");
        }
        authInfo.setPassword(jsonObject.getObject("password", String.class));
        authInfo.setUid(jsonObject.getObject("uid", Long.class));
        authInfo.setLastModifyIp(jsonObject.getObject("lastModifyIp", String.class));
        authInfo.setLastModifyTime(jsonObject.getObject("lastModifyTime", Date.class));
        BeanUtils.copyProperties(jsonObject, authInfo);
        int insert = baseMapper.insert(authInfo);
        return insert == 1;
    }


    @Override
    public Boolean changePassword(ChangePasswordDTO dto) {
        AuthInfo authInfo = baseMapper.selectOne(new LambdaQueryWrapper<AuthInfo>().eq(AuthInfo::getUid, dto.getUid()));
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String oldPasswordEncode = passwordEncoder.encode(dto.getOldPassword());
        if (oldPasswordEncode.isEmpty() || !passwordEncoder.matches(dto.getOldPassword(), authInfo.getPassword())){
            throw new CommonException(CustomHttpStatus.OLD_PASSWORD_NOT_MATCH);
        }
        authInfo.setPassword(passwordEncoder.encode(dto.getNewPassword()));
        int i = baseMapper.updateById(authInfo);
        return i == 1;
    }


    @Override
    public void getRetrievePasswordAuthCode(String email) throws Exception {
        if (!MailUtils.checkEmailIsCorrect(email)){
            throw new CommonException(CustomHttpStatus.EMAIL_FORMAT_ERROR);
        }
        if (!userFeignClient.checkEmailIsRegister(email)){
            throw new CommonException(CustomHttpStatus.EMAIL_IS_NOT_REGISTER);
        }
        String authCode = RandomAuthCodeUtil.getRandomAuthCode();
        Long expire = stringRedisTemplate.getExpire(RedisNamespace.AUTH_CODE_RETRIEVE.getFullPathKey(email));
        if (expire != null && expire >= 9L){
            throw new CommonException(CustomHttpStatus.AUTH_CODE_SEND_FREQUENT);
        }
        stringRedisTemplate.opsForValue()
                .set(RedisNamespace.AUTH_CODE_RETRIEVE.getFullPathKey(email), authCode, 10, TimeUnit.MINUTES);
         MailUtils.sendRandomCode(authCode, email, "10");
    }


    @Override
    public void retrievePassword(RetrievePasswordDTO dto) {
        String authCodeInRedis = stringRedisTemplate.opsForValue()
                .get(RedisNamespace.AUTH_CODE_RETRIEVE.getFullPathKey(dto.getEmail()));
        if (!StringUtils.hasLength(authCodeInRedis) || !authCodeInRedis.equals(dto.getAuthCode()))
            throw new CommonException(CustomHttpStatus.AUTH_CODE_ERROR);
        CommonResult userJsonByEmail = userFeignClient.getUserJsonByEmail(dto.getEmail());
        JSONObject jsonObject = JSON.parseObject(userJsonByEmail.getData().toString(), JSONObject.class);
        String uid = jsonObject.getString("id");
        AuthInfo authInfo = new AuthInfo();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        authInfo.setPassword(passwordEncoder.encode(dto.getNewPassword()));
        baseMapper.update(authInfo, new LambdaQueryWrapper<AuthInfo>().eq(AuthInfo::getUid, uid));
        stringRedisTemplate.delete(RedisNamespace.AUTH_CODE_RETRIEVE.getFullPathKey(dto.getEmail()));
    }
}
