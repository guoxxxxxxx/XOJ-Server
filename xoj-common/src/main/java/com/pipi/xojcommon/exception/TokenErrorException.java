/**
 * @Time: 2024/9/2 17:08
 * @Author: guoxun
 * @File:
 * @Description:
 */

package com.pipi.xojcommon.exception;

public class TokenErrorException extends RuntimeException{

    public TokenErrorException(){
        super();
    }


    public TokenErrorException(String message){
        super(message);
    }


    public TokenErrorException(String message, Throwable cause){
        super(message, cause);
    }
}
