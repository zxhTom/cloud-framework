package com.github.zxhtom.demo.login.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.demo.login.config
 * @date 2021/9/27 16:54
 */
@Configuration
@EnableRedisHttpSession
public class SessionConfig {
    @Bean
    public JedisConnectionFactory connectionFactory() {
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setHostName("47.121.182.177");
        redisStandaloneConfiguration.setDatabase(0);
        redisStandaloneConfiguration.setPassword(RedisPassword.of("gSZGffE33bZEcd69piVDcmdWKDv5n3CL"));
        redisStandaloneConfiguration.setPort(16379);
        return new JedisConnectionFactory(redisStandaloneConfiguration);
    }
}
