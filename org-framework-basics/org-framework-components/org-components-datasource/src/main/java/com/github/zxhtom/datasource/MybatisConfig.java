package com.github.zxhtom.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.github.zxhtom.datasource.constant.MybatisConstant;
import com.github.zxhtom.datasource.properties.MybatisLocaltionProperties;
import com.github.zxhtom.datasource.utils.MapperUtils;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
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
@ServletComponentScan
public class MybatisConfig {

    @Autowired
    MybatisLocaltionProperties mybatisLocaltionProperties;
    /**
     * @author zxhtom
     * @Description 注入数据源
     * @Date 14:22 2020年06月03日, 0003
     * @Param
     * @return javax.sql.DataSource
     */
    @Bean
    @ConfigurationProperties(prefix="spring.datasource")
    public DataSource primaryDataSource() {
        return new DruidDataSource();
    }
    /**
     * 注入事务管理器
     *
     * @return
     */
    @Bean("primaryTransactionManager")
    @Primary
    public DataSourceTransactionManager primaryTransactionManager() {
        return new DataSourceTransactionManager(primaryDataSource());
    }

    @Bean
    public SqlSessionFactory primarySqlSessionFactory() {
        SqlSessionFactory factory = null;
        try {
            SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
            sqlSessionFactoryBean.setDataSource(primaryDataSource());
            sqlSessionFactoryBean.setConfigLocation(new DefaultResourceLoader().getResource("classpath:mybatis-maltcloud.xml"));
            sqlSessionFactoryBean.setMapperLocations(MapperUtils.getInstance().getMapperLocaltions(mybatisLocaltionProperties));
            factory = sqlSessionFactoryBean.getObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return factory;
    }
}
