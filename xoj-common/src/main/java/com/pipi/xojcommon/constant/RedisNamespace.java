package com.pipi.xojcommon.constant;

public enum RedisNamespace {

    AUTH_CODE_REGISTER("auth_code:register:"),
    AUTH_CODE_LOGIN("auth_code:login:"),
    AUTH_CODE_RETRIEVE("auth_code:retrieve:");

    private final String path;


    RedisNamespace(String path){
        this.path = path;
    }


    public String getFullPathKey(String key){
        return path + key;
    }
}
