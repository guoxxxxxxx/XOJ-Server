/**
 * @Time: 2024/9/14 17:16
 * @Author: guoxun
 * @File: CommonException
 * @Description:
 */

package com.pipi.xojcommon.common;

import lombok.Data;

@Data
public class CommonException extends RuntimeException{

    private CustomHttpStatus httpStatus;

    public CommonException(String message){
        super(message);
    }


    public CommonException(CustomHttpStatus httpStatus){
        this.httpStatus = httpStatus;
    }
}
