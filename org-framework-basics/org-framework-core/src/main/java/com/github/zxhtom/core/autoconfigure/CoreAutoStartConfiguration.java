package com.github.zxhtom.core.autoconfigure;

import com.github.zxhtom.core.IdGenerator;
import com.github.zxhtom.core.IdGeneratorImpl;
import com.github.zxhtom.core.service.SystemService;
import com.github.zxhtom.core.service.impl.DefaultSystemServiceImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.core.start
 * @date 2021/8/31 16:40
 */
@Configuration
public class CoreAutoStartConfiguration{
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
}
