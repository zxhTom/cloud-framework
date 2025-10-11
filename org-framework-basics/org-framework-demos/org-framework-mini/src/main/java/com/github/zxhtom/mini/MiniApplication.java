package com.github.zxhtom.mini;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.knife4j
 * @date 2021/9/14 15:50
 */
@SpringBootApplication
public class MiniApplication {
    public static void main(String[] args) {
        new SpringApplication(MiniApplication.class).run(args);
    }
}
