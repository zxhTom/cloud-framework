package com.github.zxhtom.demo.exception.config;

import com.github.zxhtom.demo.exception.model.Tom;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * TODO
 *
 * @author zxhtom
 * 2022/10/21
 */
@Configuration
public class TomConfig {

    @Bean
    @Primary
    @ConditionalOnMissingClass(value = {"com1.github.zxhtom.demo.exception.controller.TomController"})
    public Tom tom() {
        return new Tom();
    }
}
