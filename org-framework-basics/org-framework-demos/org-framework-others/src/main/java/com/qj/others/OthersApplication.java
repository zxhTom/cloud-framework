package com.qj.others;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.qj.others
 * @date 2021/9/6 13:50
 */
@SpringBootApplication
public class OthersApplication {
    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(OthersApplication.class);
        application.run(args);
    }
}
