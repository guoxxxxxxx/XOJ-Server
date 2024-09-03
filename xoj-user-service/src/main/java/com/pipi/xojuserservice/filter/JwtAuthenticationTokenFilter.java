/**
 * @Time: 2024/9/1 19:27
 * @Author: guoxun
 * @File: JwtAuthenticationTokenFilter
 * @Description:
 */

package com.pipi.xojuserservice.filter;

import com.alibaba.fastjson2.JSON;
import com.pipi.xojcommon.exception.TokenErrorException;
import com.pipi.xojcommon.exception.TokenExpiredException;
import com.pipi.xojcommon.utils.JwtUtils;
import com.pipi.xojuserservice.pojo.domain.LoginUser;
import com.pipi.xojuserservice.utlis.JwtTokenUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Value("${server.servlet.context-path}")
    private String prefix_url;

    private static final String[] AUTH_URLS = {"/user/login", "/user/register", "/user/registerSendAuthCode"};

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = JwtUtils.getToken(request);
        List<String> authList = Arrays.asList(AUTH_URLS);
        if (authList.contains(request.getRequestURI().substring(prefix_url.length()))){
            doFilter(request, response, filterChain);
            return;
        }
        if (!StringUtils.hasLength(token))
            throw new TokenErrorException("token 不存在");
        else{
            Claims claims;
            try {
                claims = JwtUtils.parseJwt(token);
            } catch (Exception e){
                log.error(e.toString());
                throw new TokenErrorException("token 解析异常");
            }
            String claimsSubject = claims.getSubject();
            LoginUser loginUser = JSON.parseObject(claimsSubject, LoginUser.class);
            if (JwtTokenUtils.isTokenValid(loginUser, token)){
                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(loginUser
                                , null, loginUser.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContext context = SecurityContextHolder.createEmptyContext();
                context.setAuthentication(authenticationToken);
                SecurityContextHolder.setContext(context);
            }
            doFilter(request, response, filterChain);
        }
    }
}
