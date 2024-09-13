package com.pipi.xojuserservice.feign;


import com.pipi.xojcommon.common.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "xoj-auth-service", path = "/api/xoj/v1/auth")
public interface AuthFeignClient {

    @PostMapping("/ua/savePassword")
    CommonResult savePassword(@RequestBody String infoJson);
}
