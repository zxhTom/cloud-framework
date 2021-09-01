package com.github.zxhtom.demo.config;

import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.demo.config
 * @date 2021/9/1 16:28
 */
@Component
public class EnvironmentConfig implements EnvironmentAware {
    static Environment environment;
    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    public static Environment getEvn() {
        return environment;
    }
}
