package com.github.zxhtom.web.beandefinition;

import com.alibaba.fastjson.JSON;
import org.apache.catalina.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.web.beandefinition
 * @date 2021/9/30 11:15
 */
@Configuration
@Order(1)
public class AbstractBeanDefinitionRegistryPostProcessor extends PropertySourcesPlaceholderConfigurer
        implements BeanDefinitionRegistryPostProcessor , ApplicationContextAware {

    List<BeanAutoSpanAfterInitProcessor> beanAutoSpanAfterInitProcessorList;
    private ApplicationContext applicationContext;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry beanDefinitionRegistry) throws BeansException {
        /*Map<String, BeanAutoSpanAfterInitProcessor> beansOfType =
                applicationContext.getBeansOfType(BeanAutoSpanAfterInitProcessor.class);
        if (beansOfType == null || beansOfType.size() < 1) {
            BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.rootBeanDefinition(BeanAutoSpanAfterInitProcessor.class);
            beanDefinitionBuilder.getBeanDefinition().getBeanClassName();

            beanDefinitionRegistry.registerBeanDefinition(beanDefinitionBuilder.getBeanDefinition().getBeanClassName(),beanDefinitionBuilder.getBeanDefinition());
        }

        for (BeanAutoSpanAfterInitProcessor bean : beansOfType.values()) {
            Class instanceClass = bean.getInstanceClass();
            BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.rootBeanDefinition(instanceClass);
            String[] beanNamesForType = applicationContext.getBeanNamesForType(instanceClass);
            if (beanNamesForType != null && beanNamesForType.length == 1) {
                beanDefinitionRegistry.removeBeanDefinition(beanNamesForType[0]);
                beanDefinitionRegistry.registerBeanDefinition(beanNamesForType[0], beanDefinitionBuilder.getBeanDefinition());
            }
        }*/

    }
}
