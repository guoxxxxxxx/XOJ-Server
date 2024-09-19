/**
 * @Time: 2024/8/30 16:53
 * @Author: guoxun
 * @File: GlobalExceptionHandler
 * @Description:
 */

package com.pipi.xojauthservice.config;

import com.pipi.xojcommon.common.CommonResult;
import com.pipi.xojcommon.common.CustomHttpStatus;
import com.pipi.xojcommon.config.CommonGlobalExceptionHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends CommonGlobalExceptionHandler {


    /**
     * 用户密码错误异常处理器
     */
    @ExceptionHandler(BadCredentialsException.class)
    public CommonResult handleBadCredentialsException(BadCredentialsException e){
        String message;
        try {
            message = e.getMessage().split(":")[1];
        } catch (Exception e1){
            message = e.getMessage();
        }
        return new CommonResult().code(CustomHttpStatus.AUTH_GENERAL_ERROR).message(message);
    }


    /**
     * 用户被锁定异常处理器
     */
    @ExceptionHandler(LockedException.class)
    public CommonResult handleLockedException(LockedException e){
        String message;
        try {
            message = e.getMessage().split(":")[1];
        } catch (Exception e1){
            message = e.getMessage();
        }
        return new CommonResult().code(CustomHttpStatus.AUTH_GENERAL_ERROR).message(message);
    }

}
