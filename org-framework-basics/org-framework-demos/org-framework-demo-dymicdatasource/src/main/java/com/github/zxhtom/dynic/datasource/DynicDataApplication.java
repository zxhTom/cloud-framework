package com.github.zxhtom.dynic.datasource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author zxhtom
 * 2022/9/27
 */
@SpringBootApplication
public class DynicDataApplication {
    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(DynicDataApplication.class);
        application.run(args);
    }
}
