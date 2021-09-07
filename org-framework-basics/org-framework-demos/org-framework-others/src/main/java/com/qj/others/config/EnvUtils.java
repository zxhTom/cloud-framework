package com.qj.others.config;

import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.qj.others.config
 * @date 2021/9/6 15:51
 */
public class EnvUtils implements EnvironmentAware {
    private static Environment environment;
    @Override
    public void setEnvironment(Environment environment) {
        EnvUtils.environment = environment;
    }
    public static Environment getEnv() {
        return environment;
    }
}
