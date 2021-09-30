package com.github.zxhtom.demo.login.config;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.demo.login.config
 * @date 2021/9/27 17:00
 */
public class SecurityInitializer extends AbstractSecurityWebApplicationInitializer {
    public SecurityInitializer() {
        super(SessionConfig.class);
    }
}

