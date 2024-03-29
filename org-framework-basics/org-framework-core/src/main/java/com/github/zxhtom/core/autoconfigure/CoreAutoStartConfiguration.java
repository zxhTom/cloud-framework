package com.github.zxhtom.core.autoconfigure;

import com.github.zxhtom.core.IdGenerator;
import com.github.zxhtom.core.IdGeneratorImpl;
import com.github.zxhtom.core.config.ErrorConfig;
import com.github.zxhtom.core.properties.EnumProperties;
import com.github.zxhtom.core.service.SystemService;
import com.github.zxhtom.core.service.impl.DefaultSystemServiceImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.server.ErrorPageRegistrar;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.core.start
 * @date 2021/8/31 16:40
 */
@Configuration
public class CoreAutoStartConfiguration {
    @Bean
    @ConditionalOnMissingBean(IdGenerator.class)
    public IdGenerator idGenerator() {
        return new IdGeneratorImpl();
    }

    @Bean
    @ConditionalOnMissingBean
    public SystemService systemService() {
        return new DefaultSystemServiceImpl();
    }

    @Bean
    public ErrorPageRegistrar errorPageRegistrar() {
        return new ErrorConfig();
    }

    @Bean
    @ConfigurationProperties(prefix = "enum")
    @ConditionalOnMissingBean
    public EnumProperties enumProperties() {
        return new EnumProperties();
    }
}
