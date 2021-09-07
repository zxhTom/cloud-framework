package com.github.zxhtom.demo.config;

import com.github.zxhtom.demo.enums.SexEnum;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.demo.config
 * @date 2021/9/6 19:27
 */
public class StringToEnumConverter implements Converter<String, SexEnum> {
    @Override
    public SexEnum convert(String source) {
        return SexEnum.FEMALE;
    }
}
