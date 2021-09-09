package com.github.zxhtom.core.converter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.github.zxhtom.core.enums.BaseEnum;
import com.github.zxhtom.core.properties.EnumProperties;
import com.github.zxhtom.web.context.ApplicationContextUtil;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.core.converter
 * @date 2021/9/8 16:08
 */
@Component
public class BaseEnumSerializer extends JsonSerializer<BaseEnum> {

    @Override
    public void serialize(BaseEnum value, JsonGenerator gen, SerializerProvider serializers) throws IOException, IOException {
        EnumProperties enumProperties = ApplicationContextUtil.getBean(EnumProperties.class);
        if (enumProperties.isSource()) {
            //如果开启元enum输出，则直接输出枚举名称
            gen.writeObject(value.toString());
        } else {
            //否则输出BaseEnum的code作为枚举代表
            gen.writeObject(value.getCode().toString());
        }
    }

}
