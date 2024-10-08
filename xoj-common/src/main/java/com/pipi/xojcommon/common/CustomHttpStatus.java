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
     * 用户权限校验相关 110x
     */
    AUTH_GENERAL_ERROR(1100, "鉴权通用错误代码"),
    AUTH_USERNAME_OR_PASSWORD_ERROR(1101, "用户名或密码错误"),
    AUTH_TOKEN_IS_NOT_EXIST(1102, "Token不存在"),
    /**
     * 用户注册相关 111x
     */
    REGISTER_GENERAL_ERROR(1110, "注册通用错误代码"),
    REGISTER_FAIL(1111, "注册失败"),
    EMAIL_ALREADY_REGISTER(1112, "邮箱已被注册"),
    EMAIL_FORMAT_ERROR(1113, "邮箱格式错误"),
    EMAIL_IS_NOT_REGISTER(1114, "当前邮箱尚未注册"),
    /**
     * 密码修改 112x
     */
    OLD_PASSWORD_NOT_MATCH(1120, "旧密码错误"),
    /**
     * 操作错误
     */
    INSERT_ERROR(1201, "插入信息失败")
    ;


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
