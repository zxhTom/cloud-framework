package com.github.zxhtom.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ImportResource;

import javax.servlet.annotation.WebServlet;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.demo
 * @date 2021/8/23 10:20
 */
@SpringBootApplication
@ServletComponentScan("com.github.zxhtom")

public class DemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class);
    }
}
