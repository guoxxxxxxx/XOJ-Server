/**
 * @Time: 2024/8/28 15:00
 * @Author: guoxun
 * @File: CommonResult
 * @Description:
 */

package com.pipi.xojcommon.common;

import lombok.Data;

@Data
public class CommonResult<T> {

    /**
     * 消息
     */
    private String message;

    /**
     * 状态码
     */
    private Integer status;

    /**
     * 数据
     */
    private T data;

    public CommonResult<T> success(){
        this.status = 200;
        return this;
    }

    public CommonResult<T> fail(){
        this.status = 500;
        return this;
    }

    public CommonResult<T> message(String message){
        this.message = message;
        return this;
    }

    public CommonResult<T> data(T data){
        this.data = data;
        return this;
    }
}
