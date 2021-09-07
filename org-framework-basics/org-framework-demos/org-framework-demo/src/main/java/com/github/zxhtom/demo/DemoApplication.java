package com.github.zxhtom.demo;

import com.github.zxhtom.demo.config.MyApplicationContextInitializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.demo
 * @date 2021/8/23 10:20
 */
@SpringBootApplication
public class DemoApplication {
    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(DemoApplication.class);
        application.addInitializers(new MyApplicationContextInitializer());
        application.run(args);
    }
}
