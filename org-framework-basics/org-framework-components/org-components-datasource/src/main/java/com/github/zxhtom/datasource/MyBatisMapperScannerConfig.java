package com.github.zxhtom.datasource;

import com.github.zxhtom.datasource.properties.MybatisProperties;
import com.github.zxhtom.datasource.utils.MapperUtils;
import org.apache.commons.lang3.StringUtils;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.util.HashSet;
import java.util.Set;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.datasource
 * @date 2021/8/24 12:12
 * @description 手动向Sprig中注册bean;此处仅为学习，系统中没用到此功能
 */

@Configuration
@AutoConfigureAfter(MybatisConfig.class)//见文思意，在MybatisConfig配置之后加载
public class MyBatisMapperScannerConfig implements EnvironmentAware {

    private Environment environment;
    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer(MybatisProperties mybatisProperties) {
        String mapperPackage = environment.getProperty("mybatis.mapper-package");
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        String defaultMapperPackage = MapperUtils.getInstance().getMapperPackage(mybatisProperties);
        Set<String> mapperPackageSet = new HashSet<>();
        mapperPackageSet.add(mapperPackage);
        mapperPackageSet.add(defaultMapperPackage);
        Object[] mpArray = mapperPackageSet.toArray();
        String resultPackage = StringUtils.join(mpArray,",");
        mapperScannerConfigurer.setBasePackage(resultPackage);
        return mapperScannerConfigurer;
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}

