package com.github.zxhtom.utils.json;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.utils.json
 * @date 2021/10/18 14:23
 */
@Slf4j
public class JsonUtils {

    @Getter
    private static ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL); // 忽略 null 值
        // 解决 LocalDateTime 的序列化
//        SimpleModule simpleModule = new JavaTimeModule()
//                .addSerializer(LocalDateTime.class, TimestampLocalDateTimeSerializer.INSTANCE)
//                .addDeserializer(LocalDateTime.class, TimestampLocalDateTimeDeserializer.INSTANCE);
//        objectMapper.registerModules(simpleModule);
    }
    private static JsonUtils util = new JsonUtils();
    
    public static JsonUtils getInstance(){
        return util;
    }

    public static void init(ObjectMapper objectMapper) {
        JsonUtils.objectMapper = objectMapper;
    }

    @SneakyThrows
    public static String toJsonString(Object object) {
        return objectMapper.writeValueAsString(object);
    }

    public boolean isJson(String message) {
        try {
            if (StringUtils.isEmpty(message)) {
                return false;
            }
            JSONObject jsonObject = (JSONObject) JSONObject.parse(message);
            return true;
        } catch (Exception e) {
            log.error("{}数据格式不符，暂不处理：{}",message,e.getMessage());
            return false;
        }
    }

    public boolean isArrayJson(String key) {
        try {
            Object parse = JSONArray.parseArray(key);
            return true;
        } catch (Exception e) {
            log.error("{}格式化json数组出错：{}",key,e.getMessage());
        }
        return false;
    }
}
