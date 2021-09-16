package com.github.zxhtom.knife4j.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.knife4j
 * @date 2021/9/14 15:50
 */
@SpringBootApplication
public class Knife4jApplication implements WebMvcConfigurer {
    public static void main(String[] args) {
        new SpringApplication(Knife4jApplication.class).run(args);
    }
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("doc.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}
