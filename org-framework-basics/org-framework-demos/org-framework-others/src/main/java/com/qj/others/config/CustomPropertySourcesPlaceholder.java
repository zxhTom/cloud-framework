package com.qj.others.config;

import com.github.zxhtom.datasource.properties.MybatisProperties;
import lombok.NonNull;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.core.env.PropertySource;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.qj.others.config
 * @date 2021/9/6 15:46
 */
//@Configuration
@Order(value = 1)
public class CustomPropertySourcesPlaceholder extends PropertySourcesPlaceholderConfigurer
        implements BeanDefinitionRegistryPostProcessor, EnvironmentAware, ApplicationContextAware {


    private Environment environment;

    private ApplicationContext applicationContext;

    @Override
    public void setEnvironment(final Environment environment) {
        this.environment = environment;
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
    @Override
    public void postProcessBeanDefinitionRegistry(@NonNull BeanDefinitionRegistry beanDefinitionRegistry) throws BeansException {
        String property = environment.getProperty("mybatis.mapper-package");
        BeanDefinitionBuilder coreRestBean = BeanDefinitionBuilder.rootBeanDefinition(MybatisProperties.class);
        coreRestBean.addPropertyValue("mapperPackage", property);
        beanDefinitionRegistry.registerBeanDefinition("mybatisProperties", coreRestBean.getBeanDefinition());
        Object mybatisProperties = applicationContext.getBean("mybatisProperties");
        // 占位符解析
        postProcessBeanFactory((ConfigurableListableBeanFactory) beanDefinitionRegistry);

    }
}
