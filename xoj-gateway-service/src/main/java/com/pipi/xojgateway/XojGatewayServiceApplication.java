package com.pipi.xojgateway;

import com.pipi.xojgateway.feign.UserFeignClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class XojGatewayServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(XojGatewayServiceApplication.class, args);
    }

}
