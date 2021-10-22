package com.github.zxhtom.demo.common;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.demo.common
 * @date 2021/10/13 11:14
 */
@SpringBootApplication()
public class CommonApplication {
    public static void main(String[] args) {
        new SpringApplication(CommonApplication.class).run(args);
    }
}
