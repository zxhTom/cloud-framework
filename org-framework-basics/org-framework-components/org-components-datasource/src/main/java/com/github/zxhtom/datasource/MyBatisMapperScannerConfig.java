package com.github.zxhtom.datasource;

import com.github.zxhtom.datasource.properties.MybatisProperties;
import com.github.zxhtom.datasource.utils.MapperUtils;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.datasource
 * @date 2021/8/24 12:12
 */

@Configuration
@AutoConfigureAfter(MybatisConfig.class)//见文思意，在MybatisConfig配置之后加载
public class MyBatisMapperScannerConfig {

    @Autowired
    MybatisProperties mybatisProperties;
    @Autowired
    SqlSessionFactory sqlSessionFactory;
    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        //mapperScannerConfigurer.setSqlSessionFactoryBeanName(sqlSessionFactory);
        mapperScannerConfigurer.setSqlSessionFactory(sqlSessionFactory);
        mapperScannerConfigurer.setBasePackage(MapperUtils.getInstance().getMapperPackage(mybatisProperties));
        return mapperScannerConfigurer;
    }
}

