package com.github.zxhtom.core.converter;

import com.ctc.wstx.util.StringUtil;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonStreamContext;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeType;
import com.github.zxhtom.core.enums.BaseEnum;
import com.github.zxhtom.core.utils.EnumUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.core.converter
 * @date 2021/9/8 16:09
 */
public class BaseEnumDeserializer extends JsonDeserializer<BaseEnum> {

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public BaseEnum deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {

        JsonNode node = jp.getCodec().readTree(jp);
        String currentName = jp.currentName();
        Object currentValue = jp.getCurrentValue();
        Class findPropertyType = null;
        if(currentValue instanceof Collection) {

            JsonStreamContext parsingContext = jp.getParsingContext();

            JsonStreamContext parent = parsingContext.getParent();
            Object currentValue3 = parent.getCurrentValue();
            String currentName3 = parent.getCurrentName();
            try {
                Field listField = currentValue3.getClass().getDeclaredField(currentName3);
                ParameterizedType listGenericType = (ParameterizedType) listField.getGenericType();
                Type listActualTypeArguments = listGenericType.getActualTypeArguments()[0];
                findPropertyType = (Class) listActualTypeArguments;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else {
            findPropertyType = BeanUtils.findPropertyType(currentName, currentValue.getClass());
        }
        if(findPropertyType == null) {
            throw new RuntimeException("数据格式异常");
        }
        String asText = null;
        if(node.getNodeType() == JsonNodeType.STRING) {
            asText = node.asText();
        }else{
            asText = node.get("code").asText();
        }
        BaseEnum valueOf = null;
        if(StringUtils.isNotBlank(asText)){
            valueOf = EnumUtils.valueOf(asText, findPropertyType);
        }
        return valueOf;
    }


}
