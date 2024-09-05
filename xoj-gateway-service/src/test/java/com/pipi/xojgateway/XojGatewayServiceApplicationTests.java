package com.pipi.xojgateway;

import com.pipi.xojcommon.common.CommonResult;
import com.pipi.xojgateway.feign.UserFeignClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class XojGatewayServiceApplicationTests {

    @Autowired
    UserFeignClient userFeignClient;


}
