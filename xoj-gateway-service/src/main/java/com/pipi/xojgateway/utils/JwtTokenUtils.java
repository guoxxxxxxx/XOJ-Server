/**
 * @Time: 2024/9/5 9:20
 * @Author: guoxun
 * @File: JwtTokenUtils
 * @Description:
 */

package com.pipi.xojgateway.utils;

import com.alibaba.fastjson2.JSON;
import com.pipi.xojcommon.utils.JwtUtils;
import com.pipi.xojgateway.pojo.domain.LoginUser;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class JwtTokenUtils {

    /**
     * 验证数据库中的用户和jwt中的数据是否匹配
     * @param loginUser 数据库中的用户信息
     * @param jwtToken jwt数据
     * @return
     */
    public static Boolean isTokenValid(LoginUser loginUser, String jwtToken){
        Claims claims = JwtUtils.parseJwt(jwtToken);
        LoginUser jwtLoginUser = JSON.parseObject(claims.getSubject(), LoginUser.class);
        if (JwtUtils.isTokenExpired(jwtToken)){
            log.error("token 已过期");
            return false;
        }
        else if (jwtLoginUser.equals(loginUser)){
            return true;
        }
        else {
            log.error("token 数据不一致");
            return false;
        }
    }
}
