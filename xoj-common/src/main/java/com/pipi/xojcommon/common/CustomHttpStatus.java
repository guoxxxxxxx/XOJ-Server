/**
 * @Time: 2024/9/3 16:25
 * @Author: guoxun
 * @File: CustomHttpStatus
 * @Description:
 */

package com.pipi.xojcommon.common;

public enum CustomHttpStatus {
    /**
     * 系统预留 10xx
     */
    AUTH_CODE_ERROR(1001, "验证码错误"),
    AUTH_CODE_SEND_FREQUENT(1002, "验证码发送频繁"),
    EMAIL_SERVER_ERROR(1003, "邮件服务异常"),
    /**
     * 用户权限校验相关 11xx
     */
    AUTH_GENERAL_ERROR(1100, "鉴权通用错误代码"),
    AUTH_USERNAME_OR_PASSWORD_ERROR(1101, "用户名或密码错误"),
    /**
     * 用户注册相关 12xx
     */
    REGISTER_GENERAL_ERROR(1200, "注册通用错误代码"),
    REGISTER_FAIL(1201, "注册失败"),
    EMAIL_ALREADY_REGISTER(1202, "邮箱已被注册"),
    EMAIL_FORMAT_ERROR(1203, "邮箱格式错误");


    /**
     * 响应状态码
     */
    private final Integer code;

    /**
     * 响应消息
     */
    private final String message;


    CustomHttpStatus(Integer code, String message){
        this.code = code;
        this.message = message;
    }


    public Integer getCode() {
        return code;
    }


    public String getMessage() {
        return message;
    }
}
