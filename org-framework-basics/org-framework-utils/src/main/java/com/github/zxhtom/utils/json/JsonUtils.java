package com.github.zxhtom.utils.json;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
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
    private static JsonUtils util = new JsonUtils();
    
    public static JsonUtils getInstance(){
        return util;
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
