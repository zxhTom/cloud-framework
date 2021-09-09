package com.github.zxhtom.demo.config;

import com.github.zxhtom.demo.enums.SexEnum;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.demo.config
 * @date 2021/9/8 17:10
 */
public class EnumToStringFormatter implements Formatter<SexEnum> {
    @Override
    public SexEnum parse(String text, Locale locale) throws ParseException {
        return SexEnum.MALE;
    }

    @Override
    public String print(SexEnum object, Locale locale) {
        return "hello";
    }
}
