package com.github.zxhtom.datasource.beanProcessors;

import com.baomidou.mybatisplus.autoconfigure.MybatisPlusProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.demo.bean
 * @date 2021/8/26 9:19
 */
@Component
@Slf4j
public class MybatisInitialPostProcessor implements InstantiationAwareBeanPostProcessor {

    @PostConstruct
    public void init(){
    }

    public MybatisInitialPostProcessor(){
    }

    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        return null;
    }

    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        if (MybatisPlusProperties.class == bean.getClass()) {
            log.info("bean初始化后执行：Object为"+bean+"|beanName为"+beanName);
            MybatisPlusProperties properties = (MybatisPlusProperties) bean;
            String configLocation = properties.getConfigLocation();
            if (StringUtils.isEmpty(configLocation)) {
                properties.setConfigLocation("classpath:mybatis-maltcloud.xml");
            }
        }
        return true;
    }
}
