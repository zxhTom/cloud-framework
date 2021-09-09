package com.github.zxhtom.core.converter;

import com.github.zxhtom.core.enums.BaseEnum;
import com.github.zxhtom.core.utils.EnumUtils;
import org.springframework.core.convert.converter.Converter;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.core.converter
 * @date 2021/9/8 16:16
 */
class IntegerStrToEnum<T extends BaseEnum> implements Converter<String, T> {
    private final Class<T> enumType;
    private Map<String, T> enumMap = new HashMap<>();

    public IntegerStrToEnum(Class<T> enumType) {
        this.enumType = enumType;
        T[] enums = enumType.getEnumConstants();
        for (T e : enums) {
            enumMap.put(e.getCode() + "", e);
        }
    }

    @Override
    public T convert(String source) {
        T result = enumMap.get(source);
        if (result == null) {
            BaseEnum baseEnum = EnumUtils.valueOf(source, enumType);
            return (T) baseEnum;
        }
        return result;
    }
}
