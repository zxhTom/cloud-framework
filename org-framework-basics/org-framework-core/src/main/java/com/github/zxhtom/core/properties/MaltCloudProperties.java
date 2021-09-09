package com.github.zxhtom.core.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.core.properties
 * @date 2021/9/9 17:09
 */
@Data
@ConfigurationProperties(prefix = "spring.maltcloud")
public class MaltCloudProperties {
    private Listener listener;

    @Data
    public class Listener{
        private boolean open = true;
    }
}
