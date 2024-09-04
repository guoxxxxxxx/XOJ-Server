package com.pipi.xojproblemservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class XojProblemServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(XojProblemServiceApplication.class, args);
    }

}
