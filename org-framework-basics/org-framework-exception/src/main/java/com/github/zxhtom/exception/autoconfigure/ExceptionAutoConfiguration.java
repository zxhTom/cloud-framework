package com.github.zxhtom.exception.autoconfigure;

import com.github.zxhtom.exception.log.ExceptionLogInDatasource;
import com.github.zxhtom.exception.log.impl.DefaultExceptionLogInImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.exception.autoconfigure
 * @date 2021/8/31 17:18
 */
@Configuration
public class ExceptionAutoConfiguration {
    @Bean
    @ConditionalOnMissingBean
    public ExceptionLogInDatasource exceptionLogInDatasource() {
        return new DefaultExceptionLogInImpl();
    }
}
