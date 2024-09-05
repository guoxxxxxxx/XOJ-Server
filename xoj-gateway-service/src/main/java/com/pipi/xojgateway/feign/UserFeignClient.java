package com.pipi.xojgateway.feign;

import com.pipi.xojcommon.common.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "xoj-user-service")
@Component
public interface UserFeignClient {

    @GetMapping("/user/{id}")
    CommonResult getUserById(@PathVariable Integer id);
}
