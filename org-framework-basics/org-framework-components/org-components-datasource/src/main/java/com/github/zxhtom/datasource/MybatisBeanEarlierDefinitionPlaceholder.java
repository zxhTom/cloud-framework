package com.github.zxhtom.datasource;

import com.baomidou.mybatisplus.autoconfigure.MybatisPlusProperties;
import com.github.zxhtom.datasource.constant.MybatisConstant;
import com.github.zxhtom.datasource.properties.MybatisProperties;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;

import java.util.HashSet;
import java.util.Set;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.qj.others.config
 * @date 2021/9/6 15:51
 */
@Order(value = 1)
@AutoConfigureBefore(MybatisConfig.class)
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
        String property = environment.getProperty("mybatis.type_handlers_package");
        Set<String> typeHandlersPackageSet = new HashSet<>();
        if (StringUtils.isNotEmpty(property)) {
            typeHandlersPackageSet.add(property);
        }
        typeHandlersPackageSet.add(MybatisConstant.TYPEHANDLERSPACKAGE);
        Object[] mpArray = typeHandlersPackageSet.toArray();
        String typeHandlersPackage = StringUtils.join(mpArray,",");
        BeanDefinitionBuilder coreRestBean = BeanDefinitionBuilder.rootBeanDefinition(MybatisPlusProperties.class);
        coreRestBean.addPropertyValue("typeHandlersPackage", typeHandlersPackage);
        //beanDefinitionRegistry.registerBeanDefinition("mybatisPlusProperties", coreRestBean.getBeanDefinition());
    }


}
