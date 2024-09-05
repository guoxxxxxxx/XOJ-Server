/**
 * @Time: 2024/9/3 16:09
 * @Author: guoxun
 * @File: AuthExceptionHandler
 * @Description:
 */

package com.pipi.xojgateway.config;


import com.pipi.xojcommon.common.CommonResult;
import com.pipi.xojcommon.common.CustomHttpStatus;
import com.pipi.xojcommon.exception.TokenErrorException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
@Slf4j
public class AuthExceptionHandler {


    /**
     *
     * @param e
     * @return
     */
    @ExceptionHandler(InternalAuthenticationServiceException.class)
    public CommonResult handleInternalAuthenticationServiceException(InternalAuthenticationServiceException e){
        log.info(e.toString());
        return new CommonResult().code(CustomHttpStatus.AUTH_GENERAL_ERROR)
                .data(e.toString().split(": ")[1]);
    }


    /**
     * 用户名或密码错误
     * @param e
     * @return
     */
    @ExceptionHandler(BadCredentialsException.class)
    public CommonResult handleBadCredentialsException(BadCredentialsException e){
        log.info("当前用户，用户名或密码错误" + e.toString());
        return new CommonResult().code(CustomHttpStatus.AUTH_USERNAME_OR_PASSWORD_ERROR)
                .data(e.toString().split(": ")[1]);
    }
}
