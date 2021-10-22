package com.github.zxhtom.common.processor;

import com.github.zxhtom.core.annotaion.SuperDirection;
import com.github.zxhtom.core.annotaion.SuperDirectionHandler;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.annotation.AnnotationBeanNameGenerator;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.type.filter.AnnotationTypeFilter;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.common.processor
 * @date 2021/10/13 11:11
 */
@Configuration
public class DefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor {
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry beanDefinitionRegistry) throws BeansException {
        ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(beanDefinitionRegistry);
        scanner.setBeanNameGenerator(new AnnotationBeanNameGenerator());
        // 定义需要扫描的注解 -- 自定义注解
        scanner.addIncludeFilter(new AnnotationTypeFilter(SuperDirectionHandler.class));
        // 定义扫描的包
        //scanner.scan("com.github.zxhtom.common");
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

    }
}
