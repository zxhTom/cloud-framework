package com.github.zxhtom.demo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.demo
 * @date 2021/9/3 11:54
 */
public class JavaTest {
    public static void main(String[] args) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("(yyyy-MM-dd)|(yyyyMMdd)");
        String date1 = "20210903";
        String date2 = "2021-09-03";
        Date parse = sdf.parse(date1);
        Date parse1 = sdf.parse(date2);
        System.out.println("@@@@@");
    }
}
