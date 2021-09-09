package com.github.zxhtom.demo.config;

import com.github.zxhtom.demo.enums.SexEnum;
import org.springframework.core.convert.converter.Converter;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.demo.config
 * @date 2021/9/6 19:27
 */
public class EnumToStringConverter implements Converter<SexEnum,String> {

    @Override
    public String convert(SexEnum source) {
        return "hello";
    }
}
