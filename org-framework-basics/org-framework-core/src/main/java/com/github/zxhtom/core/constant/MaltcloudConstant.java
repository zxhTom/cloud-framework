package com.github.zxhtom.core.constant;

import java.text.SimpleDateFormat;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.core.constant
 * @date 2021/9/3 9:46
 * @description 定义系统全局通用变量
 */
public class MaltcloudConstant {

    public final static String COMMON_DATE_FORMAT_PATTERN =  "yyyy-MM-dd HH:mm:ss";
    public final static String DATEKEY_FORMAT_PATTERN =  "yyyyMMdd";
    public final static String MONTHKEY_FORMAT_PATTERN =  "yyyyMM";
    public final static String YEAR_FORMAT_PATTERN =  "yyyy";
    /**普通时间格式*/
    public final static SimpleDateFormat COMMON_DATE_FORMAT = new SimpleDateFormat(COMMON_DATE_FORMAT_PATTERN);
    /**精确到天格式  20210903*/
    public final static SimpleDateFormat DATEKEY_FORMAT = new SimpleDateFormat(DATEKEY_FORMAT_PATTERN);
    /**精确到月格式  202109*/
    public final static SimpleDateFormat MONTHKEY_FORMAT = new SimpleDateFormat(MONTHKEY_FORMAT_PATTERN);
    /**精确到年格式  2021*/
    public final static SimpleDateFormat YEAR_FORMAT = new SimpleDateFormat(YEAR_FORMAT_PATTERN);

    public static final String LOGIN_ROLE = "ROLE_login";
    public static final String SUPERADMIN = "superAdmin";
}
