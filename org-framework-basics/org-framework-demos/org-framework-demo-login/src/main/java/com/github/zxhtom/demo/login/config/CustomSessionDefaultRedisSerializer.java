package com.github.zxhtom.demo.login.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.demo.login.config
 * @date 2021/9/27 17:24
 */
//@Component("springSessionDefaultRedisSerializer")
public class CustomSessionDefaultRedisSerializer extends JdkSerializationRedisSerializer {

    private static final Logger LOG = LoggerFactory.getLogger(CustomSessionDefaultRedisSerializer.class);

    @Override
    public byte[] serialize(Object object) {
        return serializer().serialize(object);
    }

    @Override
    public Object deserialize(@Nullable byte[] bytes) {
        return serializer().deserialize(bytes);
    }

    public Jackson2JsonRedisSerializer serializer() {
        Jackson2JsonRedisSerializer serializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        mapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        serializer.setObjectMapper(mapper);
        return serializer;
    }
}
