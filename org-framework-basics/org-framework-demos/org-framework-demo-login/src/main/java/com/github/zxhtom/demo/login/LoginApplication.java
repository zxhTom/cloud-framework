package com.github.zxhtom.demo.login;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.demo.login
 * @date 2021/9/15 9:31
 */
@SpringBootApplication
public class LoginApplication {
    public static void main(String[] args) {
        new SpringApplication(LoginApplication.class).run(args);
    }
}
