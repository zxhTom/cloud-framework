package com.github.zxhtom.datasource;

import com.github.zxhtom.datasource.properties.MybatisProperties;
import org.springframework.beans.BeansException;
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

/**
 * @author 张新华
 * @version V1.0
 * @Package com.qj.others.config
 * @date 2021/9/6 15:51
 */
//@Configuration
//@Order(value = 1)
public class MybatisBeanEarlierDefinitionPlaceholder extends PropertySourcesPlaceholderConfigurer
        implements BeanDefinitionRegistryPostProcessor, EnvironmentAware, ApplicationContextAware {
    private  Environment environment;
    private ApplicationContext applicationContext;
    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry beanDefinitionRegistry) throws BeansException {
        String property = environment.getProperty("mybatis.mapper-package");
        BeanDefinitionBuilder coreRestBean = BeanDefinitionBuilder.rootBeanDefinition(MybatisProperties.class);
        coreRestBean.addPropertyValue("mapperPackage", property);
        beanDefinitionRegistry.registerBeanDefinition("mybatisProperties", coreRestBean.getBeanDefinition());
        Object mybatisProperties = applicationContext.getBean("mybatisProperties");
    }


}
