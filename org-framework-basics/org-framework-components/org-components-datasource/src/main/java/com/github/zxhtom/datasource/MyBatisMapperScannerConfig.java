package com.github.zxhtom.datasource;

import com.github.zxhtom.datasource.properties.MybatisLocaltionProperties;
import com.github.zxhtom.datasource.utils.MapperUtils;
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
@AutoConfigureAfter(MybatisConfig.class)//见文思意，在MyBatisConfig配置之后加载
public class MyBatisMapperScannerConfig {

    @Autowired
    MybatisLocaltionProperties mybatisLocaltionProperties;
    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("primarySqlSessionFactory");
        mapperScannerConfigurer.setBasePackage(MapperUtils.getInstance().getMapperPackage(mybatisLocaltionProperties));
        return mapperScannerConfigurer;
    }
}

