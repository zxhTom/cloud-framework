package com.github.zxhtom.dynic.datasource.aop;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Configuration
@Slf4j
public class DruidDBConfig {


    @Resource
    DataSource dataSource;
/*
    @Bean
    @Primary
    @Qualifier("mainDataSource")
    public DataSource dataSource() throws SQLException {
        DruidDataSource datasource = new DruidDataSource();
        return datasource;
    }*/

    @Bean(name = "dynamicDataSource")
    @Qualifier("dynamicDataSource")
    public DynamicRoutingDataSource dynamicDataSource() throws SQLException {
        DynamicRoutingDataSource dynamicDataSource = new DynamicRoutingDataSource();
        dynamicDataSource.setDebug(false);
        //配置缺省的数据源
        dynamicDataSource.setDefaultTargetDataSource(dataSource);
        Map<Object, Object> targetDataSources = new HashMap<Object, Object>();
        //额外数据源配置 TargetDataSources
        targetDataSources.put("mainDataSource", dataSource);
        dynamicDataSource.setTargetDataSources(targetDataSources);
        return dynamicDataSource;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        MybatisSqlSessionFactoryBean sqlSessionFactoryBean = new MybatisSqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dynamicDataSource());
        //对新的SqlSessionFactory配置 修改mybatis-plus Page自动分页失效问题 以及 找不到xml问题
        MybatisConfiguration configuration = new MybatisConfiguration();
        sqlSessionFactoryBean.setConfiguration(configuration);
        return sqlSessionFactoryBean.getObject();
    }

    /**
     * @Description: 将动态数据加载类添加到事务管理器
     * @param dataSource
     * @return org.springframework.jdbc.datasource.DataSourceTransactionManager
     */
    @Bean("primaryTransactionManager")
    @Primary
    public DataSourceTransactionManager transactionManager(DynamicRoutingDataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}

