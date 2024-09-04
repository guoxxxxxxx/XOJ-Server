package com.pipi.xojuserservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication(scanBasePackages = "com.pipi")
@EnableDiscoveryClient
public class XojUserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(XojUserServiceApplication.class, args);
    }

}
