package com.github.zxhtom.datasource.beanProcessors;

import com.baomidou.mybatisplus.autoconfigure.MybatisPlusProperties;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.github.zxhtom.datasource.constant.MybatisConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.demo.bean
 * @date 2021/8/26 9:17
 */
@Component
@Slf4j
public class MybatisPropertiesBeanProcessor implements BeanPostProcessor {
    Integer index = 0;
    @PostConstruct
    public void init(){
    }

    public MybatisPropertiesBeanProcessor(){
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (MybatisPlusProperties.class == bean.getClass()&&index++==0) {
            MybatisPlusProperties properties = (MybatisPlusProperties) bean;
            String[] mapperLocations = properties.getMapperLocations();
            int mybatiSourceLength = mapperLocations.length;
            int defaultSourceLength = MybatisConstant.MAPPERLOCALTIONS.length;
            String[] newMapperLocations = new String[mybatiSourceLength + defaultSourceLength];
            System.arraycopy(mapperLocations, 0, newMapperLocations, 0, mybatiSourceLength);
            System.arraycopy(MybatisConstant.MAPPERLOCALTIONS, 0, newMapperLocations, mybatiSourceLength, defaultSourceLength);
            properties.setMapperLocations(newMapperLocations);
            log.info(bean+"======内置MybatisPlus配置信息，包含mybatis的setting和xml扫描路径======"+ beanName);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (MybatisPlusProperties.class == bean.getClass()) {
            log.info("判断是否通过其它途径设置了configuration配置，如设置，configurationLocaltion则失效");
            MybatisPlusProperties properties = (MybatisPlusProperties) bean;
            String configLocation = properties.getConfigLocation();
            MybatisConfiguration configuration = properties.getConfiguration();
            if (null != configuration) {
                return bean;
            }
            if (StringUtils.isEmpty(configLocation)) {
                properties.setConfigLocation("classpath:mybatis-maltcloud.xml");
            }
        }
        return bean;
    }
}
