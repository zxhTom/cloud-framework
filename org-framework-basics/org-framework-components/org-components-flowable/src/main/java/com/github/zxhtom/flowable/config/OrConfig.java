package com.github.zxhtom.flowable.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DelegatingWebMvcConfiguration;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

/**
 * TODO
 *
 * @author zxhtom
 * 2023/9/14
 */
@Configuration
public class OrConfig extends DelegatingWebMvcConfiguration {
    @Override
    protected RequestMappingHandlerMapping createRequestMappingHandlerMapping() {
        CustomRequestMappingHandlerMapping customRequestMappingHandlerMapping = new CustomRequestMappingHandlerMapping();
        customRequestMappingHandlerMapping.setOrder(0);
        return customRequestMappingHandlerMapping;
    }
}
