package com.github.zxhtom.demo.config;

import com.github.zxhtom.core.constant.MaltcloudConstant;
import org.springframework.core.convert.converter.Converter;

import java.util.Date;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.demo.config
 * @date 2021/9/6 19:06
 */
public class DateToStringConverter implements Converter<Date,String> {
    @Override
    public String convert(Date source) {
        String format = MaltcloudConstant.DATEKEY_FORMAT.format(source);
        return format;
    }
}
