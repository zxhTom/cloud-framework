package com.github.zxhtom.exception.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.exception.properties
 * @date 2021/8/31 15:54
 */
@ConfigurationProperties(prefix = "log")
@Component
@Data
public class LogFormat {
    private String format = "%s";
}
