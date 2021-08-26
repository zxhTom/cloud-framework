package com.github.zxhtom.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.autoconfigure.MybatisPlusAutoConfiguration;
import com.baomidou.mybatisplus.autoconfigure.MybatisPlusProperties;
import com.github.zxhtom.datasource.properties.MybatisLocaltionProperties;
import com.github.zxhtom.datasource.utils.MapperUtils;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.datasource
 * @date 2021/8/23 17:19
 */
@Configuration
@ImportResource("classpath:transaction-maltcloud.xml")
@AutoConfigureBefore(MybatisPlusAutoConfiguration.class)
public class MybatisConfig{

    @Autowired
    MybatisLocaltionProperties mybatisLocaltionProperties;
    @Autowired
    DataSource dataSource;

    /**
    * @author zxhtom
    * @Description 覆盖myabtisPlus原有属性映射前缀mybatis-plus
    * @Date 9:37 2021/8/26
    */
    @Bean
    @ConfigurationProperties(prefix="mybatis")
    @Primary
    public MybatisPlusProperties mybatisPlusProperties() {
        return new MybatisPlusProperties();
    }
    /**
     * 注入事务管理器
     *
     * @return
     */
    @Bean("primaryTransactionManager")
    @ConditionalOnMissingBean
    public DataSourceTransactionManager primaryTransactionManager() {
        return new DataSourceTransactionManager(dataSource);
    }

    //@Bean
    //@ConditionalOnMissingBean
    public SqlSessionFactory primarySqlSessionFactory() {
        SqlSessionFactory factory = null;
        try {
            SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
            sqlSessionFactoryBean.setDataSource(dataSource);
            sqlSessionFactoryBean.setConfigLocation(new DefaultResourceLoader().getResource("classpath:mybatis-maltcloud.xml"));
            sqlSessionFactoryBean.setMapperLocations(MapperUtils.getInstance().getMapperLocaltions(mybatisLocaltionProperties));
            factory = sqlSessionFactoryBean.getObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return factory;
    }
}
