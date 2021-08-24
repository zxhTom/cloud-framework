package com.github.zxhtom.datasource.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.datasource.properties
 * @date 2021/8/24 10:53
 */
@Data
@ConfigurationProperties(prefix = "mybatis")
public class MybatisLocaltionProperties {
    /**xml扫描路径**/
    private String[] mapperLocations;
    /**mapper扫描路径**/
    private String mapperPackage="";
}
