package com.github.zxhtom.mini.model;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * TODO
 *
 * @author zxhtom
 * 10/3/25
 */

// WechatConfig.java
@Configuration
@ConfigurationProperties(prefix = "wechat.mini-program")
@Data // Lombok 注解，自动生成getter/setter
public class WechatConfig {
    private String appid;
    private String secret;
}
