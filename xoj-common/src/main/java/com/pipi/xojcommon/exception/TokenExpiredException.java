/**
 * @Time: 2024/9/2 17:08
 * @Author: guoxun
 * @File:
 * @Description:
 */

package com.pipi.xojcommon.exception;

public class TokenExpiredException extends RuntimeException{

    public TokenExpiredException(){
        super();
    }


    public TokenExpiredException(String message){
        super(message);
    }


    public TokenExpiredException(String message, Throwable cause){
        super(message, cause);
    }
}
