package com.github.zxhtom.core.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.core.properties
 * @date 2021/9/9 9:38
 */
@Data
public class EnumProperties {
    private boolean source = true;
}
