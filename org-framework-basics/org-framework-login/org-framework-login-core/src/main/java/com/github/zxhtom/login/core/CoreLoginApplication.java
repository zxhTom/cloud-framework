package com.github.zxhtom.login.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.login.core
 * @date 2021/9/15 9:40
 */
@SpringBootApplication
public class CoreLoginApplication {
    public static void main(String[] args) {
        new SpringApplication(CoreLoginApplication.class).run(args);
    }
}
