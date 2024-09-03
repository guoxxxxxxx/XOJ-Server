/**
 * @Time: 2024/8/28 15:00
 * @Author: guoxun
 * @File: CommonResult
 * @Description:
 */

package com.pipi.xojcommon.common;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.Date;
import java.util.Map;

@Data
public class CommonResult {

    /**
     * 时间戳
     */
    private Date timestamp = new Date();

    /**
     * 消息
     */
    private String message = "方法请求成功";

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 数据
     */
    private Object data;


    public CommonResult success(){
        this.code = 200;
        return this;
    }


    public CommonResult fail(){
        this.code = 500;
        return this;
    }


    public CommonResult code(CustomHttpStatus customHttpStatus){
        this.code = customHttpStatus.getCode();
        this.message = customHttpStatus.getMessage();
        return this;
    }


    public CommonResult code(HttpStatus httpStatus){
        this.code = httpStatus.value();
        return this;
    }


    public CommonResult message(String message){
        this.message = message;
        return this;
    }


    public CommonResult data(Object data){
        this.data = data;
        return this;
    }


    public CommonResult data(String key, Object value){
        data = Map.of(key, value);
        return this;
    }
}
