package com.github.zxhtom.demo.config;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.convert.converter.Converter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.demo.config
 * @date 2021/9/3 11:37
 */
public class StringToDateConverter implements Converter<String, Date> {

    private static final List<String> formarts = new ArrayList<>(5);

    static{
        formarts.add("yyyy-MM");
        formarts.add("yyyy-MM-dd");
        formarts.add("yyyy-MM-dd hh");
        formarts.add("yyyy-MM-dd hh:mm");
        formarts.add("yyyy-MM-dd hh:mm:ss");
    }

    @Override
    public Date convert(String source) {
        String value = source.trim();
        if ("".equals(value)) {
            return null;
        }
        //判断日期格式是否为yyyy-MM
        if(source.matches("^\\d{4}-\\d{1,2}$")){
            return parseDate(source, formarts.get(0));
        }
        //判断日期格式是否为yyyy-MM-dd
        else if(source.matches("^\\d{4}-\\d{1,2}-\\d{1,2}$")){
            return parseDate(source, formarts.get(1));
        }
        //判断日期格式是否为yyyy-MM-dd hh
        else if(source.matches("^\\d{4}-\\d{1,2}-\\d{1,2} {1}\\d{1,2}")){
            return parseDate(source, formarts.get(2));
        }
        //判断日期格式是否为yyyy-MM-dd hh:mm
        else if(source.matches("^\\d{4}-\\d{1,2}-\\d{1,2} {1}\\d{1,2}:\\d{1,2}$")){
            return parseDate(source, formarts.get(3));
        }
        //判断日期格式是否为yyyy-MM-dd hh:mm:ss
        else if(source.matches("^\\d{4}-\\d{1,2}-\\d{1,2} {1}\\d{1,2}:\\d{1,2}:\\d{1,2}$")){
            return parseDate(source, formarts.get(4));
        }
        //判断日期字符串是否为纯数字，也就是时间戳
        else if(source.matches("^\\d+$")) {
            return new Date(Long.valueOf(source));
        }
        else {
            throw new IllegalArgumentException("Invalid boolean value '" + source + "'");
        }
    }

    public  Date parseDate(String dateStr, String format) {
        Date date=null;
        try {
            DateFormat dateFormat = new SimpleDateFormat(format);
            date = dateFormat.parse(dateStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }
}
