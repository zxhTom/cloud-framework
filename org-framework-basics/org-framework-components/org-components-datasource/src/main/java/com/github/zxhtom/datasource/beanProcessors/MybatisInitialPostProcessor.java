package com.github.zxhtom.datasource.beanProcessors;

import lombok.extern.slf4j.Slf4j;
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
        return true;
    }
}
