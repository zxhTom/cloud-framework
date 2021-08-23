package com.github.zxhtom.demo;

import com.github.zxhtom.web.WebApplication;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.demo
 * @date 2021/8/23 10:20
 */
@SpringBootApplication
@MapperScan("com.github.zxhtom.demo.mapper")
public class DemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class);
    }
}
