package com.github.zxhtom.demo.config;

import com.github.zxhtom.web.auths.OnlineSecurity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.demo.config
 * @date 2021/8/26 16:05
 */
@Configuration
public class Test {
    @Bean
    public OnlineSecurity onlineSecurity() {
        return new OnlineSecurity() {
            @Override
            public Object getOnlinePrincipal() {
                return null;
            }

            @Override
            public String getOnlineUserName() {
                return "hello world";
            }

            @Override
            public String getInterfaceName() {
                return null;
            }

        };
    }
}
