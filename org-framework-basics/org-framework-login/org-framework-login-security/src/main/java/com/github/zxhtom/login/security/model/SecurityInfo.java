package com.github.zxhtom.login.security.model;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "zxhtom.security")
@Component
@Data
public class SecurityInfo {
    private InteractionEnum interaction;
}
