package com.github.zxhtom.demo.exception;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.demo.exception
 * @date 2021/8/31 14:09
 */
@SpringBootApplication
public class ExceptionApplication {
    public static void main(String[] args) {
        new SpringApplication(ExceptionApplication.class).run(args);
    }
}
