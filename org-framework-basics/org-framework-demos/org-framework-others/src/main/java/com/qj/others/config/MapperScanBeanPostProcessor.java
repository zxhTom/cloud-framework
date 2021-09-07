package com.qj.others.config;

import com.qj.others.mapper.TestMapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.qj.others.config
 * @date 2021/9/6 15:28
 */
@Component
public class MapperScanBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if ("sqlSessionFactory".equals(beanName)) {
            SqlSessionFactory sqlSessionFactory = (SqlSessionFactory) bean;
            sqlSessionFactory.getConfiguration().addMapper(TestMapper.class);
        }
        return bean;
    }
}
