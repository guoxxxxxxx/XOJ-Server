/**
 * @Time: 2024/9/5 17:33
 * @Author: guoxun
 * @File: ResponseUtils
 * @Description:
 */

package com.pipi.xojcommon.utils;

import com.alibaba.fastjson2.JSON;
import com.pipi.xojcommon.common.CommonResult;

public class ResponseUtils {


    /**
     * 通过反射机制生成data中数据的实体
     * @param response
     * @param clazz
     * @return
     * @param <T>
     */
    public static <T> T getObjectFromCommonResult(CommonResult response, Class<T> clazz){
        return JSON.parseObject(response.getJsonData(), clazz);
    }
}
