package com.github.zxhtom.datasource.properties;

import lombok.Data;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.datasource.properties
 * @date 2021/8/24 10:53
 */
@Data
@ConfigurationProperties(prefix = "mybatis")
public class MybatisProperties implements InitializingBean {
    /**xml扫描路径**/
    private String[] mapperLocations;
    /**mapper扫描路径**/
    private String mapperPackage="";
    /**批量处理最大数量**/
    private Integer maxBatchSize = 100;

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println();
    }
}
