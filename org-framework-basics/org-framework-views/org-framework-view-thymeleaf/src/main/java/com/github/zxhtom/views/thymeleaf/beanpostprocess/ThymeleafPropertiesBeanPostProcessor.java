package com.github.zxhtom.views.thymeleaf.beanpostprocess;

import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.autoconfigure.thymeleaf.ThymeleafProperties;
import org.springframework.stereotype.Component;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.views.thymeleaf.beanpostprocess
 * @date 2021/9/16 16:04
 */
@Component
public class ThymeleafPropertiesBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName){
        if (ThymeleafProperties.class == bean.getClass()) {
            ThymeleafProperties properties = (ThymeleafProperties) bean;
            String prefix = properties.getPrefix();
            properties.setPrefix(String.format("classpath:/templates/", prefix));
        }
        return bean;
    }
}
