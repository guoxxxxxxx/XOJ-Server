package com.pipi.xojauthservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "xoj-user-service", path = "/api/xoj/v1/user")
public interface UserFeignClient {

    @GetMapping("/getUserJsonByEmail")
    String getUserJsonByEmail(@RequestParam String email);
}
