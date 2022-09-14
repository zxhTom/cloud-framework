package com.github.zxhtom.demo.transactional;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * TODO
 *
 * @author zxhtom
 * 2022/1/23
 */
@SpringBootApplication
public class TransactionApplication {
    public static void main(String[] args) {
        new SpringApplication(TransactionApplication.class).run(args);
    }
}
