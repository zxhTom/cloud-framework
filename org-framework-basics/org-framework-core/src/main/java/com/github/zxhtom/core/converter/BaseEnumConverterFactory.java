package com.github.zxhtom.core.converter;

import com.github.zxhtom.core.enums.BaseEnum;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.core.converter
 * @date 2021/9/8 15:03
 */
public class BaseEnumConverterFactory implements ConverterFactory<String, BaseEnum> {
    private static final Map<Class, Converter> converterMap = new WeakHashMap<>();
    @Override
    public <T extends BaseEnum> Converter<String, T> getConverter(Class<T> targetType) {
        Converter result = converterMap.get(targetType);
        if (result == null) {
            result = new IntegerStrToEnum<T>(targetType);
            converterMap.put(targetType, result);
        }
        return result;
    }

}
