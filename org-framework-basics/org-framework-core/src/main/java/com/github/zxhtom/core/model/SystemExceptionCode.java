package com.github.zxhtom.core.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.core.model
 * @date 2021/8/31 19:15
 */
@Data
@AllArgsConstructor
public class SystemExceptionCode {
    /**需要配置的异常类*/
    private Class clazz;

    /**需要配置的异常类，系统内置很多异常code 。自定义code已1开头的五位数字，系统会进行校验*/
    private Integer code;
}
