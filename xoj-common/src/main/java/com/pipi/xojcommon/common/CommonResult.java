/**
 * @Time: 2024/8/28 15:00
 * @Author: guoxun
 * @File: CommonResult
 * @Description:
 */

package com.pipi.xojcommon.common;

import com.alibaba.fastjson2.JSON;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private Integer status;

    /**
     * 数据
     */
    private Object data;


    public CommonResult success(){
        this.status = 200;
        return this;
    }


    public CommonResult fail(){
        this.status = 500;
        return this;
    }


    public CommonResult code(CustomHttpStatus customHttpStatus){
        this.status = customHttpStatus.getCode();
        this.message = customHttpStatus.getMessage();
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


    @JsonIgnore
    public String getJsonData(){
        return JSON.toJSONString(this.data);
    }
}
