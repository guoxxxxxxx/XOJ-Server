package com.pipi.xojcommon.constant;

public enum RedisNamespace {

    /**
     * 注册验证码
     */
    AUTH_CODE_REGISTER("auth_code:register:"),
    /**
     * 登录验证码
     */
    AUTH_CODE_LOGIN("auth_code:login:"),
    /**
     * 密码找回验证码
     */
    AUTH_CODE_RETRIEVE("auth_code:retrieve:"),
    /**
     * 注册令牌
     */
    REGISTER_TOKEN("token:register:")
    ;

    private final String path;


    RedisNamespace(String path){
        this.path = path;
    }


    public String getFullPathKey(String key){
        return path + key;
    }
}
