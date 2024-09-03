/**
 * @Time: 2024/8/30 9:34
 * @Author: guoxun
 * @File: JWTUtil
 * @Description:
 */


package com.pipi.xojcommon.utils;


import com.alibaba.fastjson2.JSON;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.*;

/**
 * jwt工具类
 */
public class JwtUtils {

    public static final Long JWT_TTL = 1000L * 60 * 60 * 24 * 7;        // one week

    public static String SECRET_KEY = "qW21YIU&^%$";


    public static String getUUID(){
        return UUID.randomUUID().toString().replace("-", "");
    }


    /**
     * 生成jwt
     * @param subject token中所需存放的数据 json格式
     * @return
     */
    public static String createJWT(String subject){
        return getJwtBuilder(subject, null, getUUID()).compact();
    }


    /**
     * 生成jwt
     * @param subject token中所需存放的数据 json格式
     * @param ttlMillis 过期时间
     * @return
     */
    public static String createJWT(String subject, Long ttlMillis){
        return getJwtBuilder(subject, ttlMillis, getUUID()).compact();
    }


    /**
     * 生成jwt
     * @param subject token中所保存的数据
     * @param ttlMillis 过期时间
     * @param id randomId
     * @return
     */
    public static String createJWT(String subject, Long ttlMillis, String id){
        return getJwtBuilder(subject, ttlMillis, id).compact();
    }


    /**
     * 生成加密后的密钥
     * @return
     */
    public static SecretKey generateKey() {
        byte[] keyBytes = Base64.getMimeDecoder().decode(SECRET_KEY);
        return new SecretKeySpec(keyBytes, 0, keyBytes.length, "AES");
    }


    /**
     * 获取jwt构建对象
     * @param subject
     * @param ttlMillis
     * @param uuid
     * @return
     */
    public static JwtBuilder getJwtBuilder(String subject, Long ttlMillis, String uuid){
        SecretKey secretKey = generateKey();
        long currentTimeMillis = System.currentTimeMillis();
        Date currentDate = new Date(currentTimeMillis);
        if (ttlMillis == null)
            ttlMillis = JWT_TTL;
        long expiredMillis = currentTimeMillis + ttlMillis;
        Date expiredDate = new Date(expiredMillis);
        return Jwts.builder()
                .setId(uuid)        // 唯一id
                .setSubject(subject)        // 设置所需签名的内容
                .setIssuedAt(currentDate)
                .signWith(SignatureAlgorithm.HS256 ,secretKey)        // 签名的key
                .setExpiration(expiredDate);
    }


    /**
     * 解析jwt对象
     * @param jwtToken token
     * @return
     * @throws Exception
     */
    public static Claims parseJwt(String jwtToken){
        return Jwts.parser()
                .setSigningKey(generateKey())
                .parseClaimsJws(jwtToken)
                .getBody();
    }


    /**
     * 获取请求token
     * @param request
     * @return
     */
    public static String getToken(HttpServletRequest request){
        String token = request.getHeader("Authorization");
        String tokenHead = "Bearer";
        if (token == null){
            token = request.getHeader("token");
        }
        else if (token.contains(tokenHead)){
            token = token.substring(tokenHead.length());
        }
        if (StringUtils.hasLength(token)){
            token = null;
        }
        return token;
    }


    /**
     * 判断token是否过期
     * @param token
     * @return
     */
    public static Boolean isTokenExpired(String token){
        Claims claims = parseJwt(token);
        Date expiration = claims.getExpiration();
        return expiration.before(new Date());
    }


    public static void main(String[] args) throws Exception{
        Map<String, String> info = new HashMap<>();
        info.put("username", "jack");
        info.put("password", "123455");
        String subject = JSON.toJSONString(info);
        String jwt = createJWT(subject);
        System.out.println("length: " + jwt.length() + "jwt: " + jwt);
        Claims claims = parseJwt(jwt);
        System.out.println(claims);
    }
}
