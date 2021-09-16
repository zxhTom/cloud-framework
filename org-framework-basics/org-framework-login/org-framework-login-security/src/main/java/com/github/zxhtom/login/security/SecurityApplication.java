package com.github.zxhtom.login.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.login.security
 * @date 2021/9/15 9:27
 */
@SpringBootApplication
public class SecurityApplication {
    public static void main(String[] args) {
        new SpringApplication(SecurityApplication.class).run(args);
    }
}
