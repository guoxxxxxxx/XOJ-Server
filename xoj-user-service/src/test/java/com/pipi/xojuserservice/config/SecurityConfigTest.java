/**
 * @Time: 2024/9/2 10:25
 * @Author: guoxun
 * @File: SecurityConfigTest
 * @Description:
 */

package com.pipi.xojuserservice.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
public class SecurityConfigTest {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void passwordTest(){
        String password = "123456";
        String encode1 = passwordEncoder.encode(password);
        System.out.println("encode1 = " + encode1);
        String encode2 = passwordEncoder.encode(password);
        System.out.println("encode2 = " + encode2);
    }

    @Test
    public void validPassword(){
        String encodePassword = "$2a$10$6VYc5auIfqZs5IIYypMcG.LaXmMmrFHAY3Ys574H5OzUcq5.eJP7G";
        boolean matches = passwordEncoder.matches("123456", encodePassword);
        System.out.println(matches ? "correct" : "fail");
    }
}
