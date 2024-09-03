package com.pipi.xojuserservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.pipi")
public class XojUserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(XojUserServiceApplication.class, args);
    }

}
