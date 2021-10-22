package com.github.zxhtom.demo.config;

import com.alibaba.fastjson.JSONObject;
import com.github.zxhtom.web.auths.OnlineSecurity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Set;

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
            public JSONObject getOnlinePrincipal() {
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

            @Override
            public boolean hasAnyRole(String... role) {
                return false;
            }

            @Override
            public Set<String> getRoleNames() {
                return null;
            }

        };
    }
}
