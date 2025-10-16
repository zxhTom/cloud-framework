package com.github.zxhtom.core.enums;

import cn.hutool.core.util.ArrayUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * 时间间隔的枚举
 *
 * @author dhb52
 */
@Getter
@AllArgsConstructor
public enum DateIntervalEnum implements BaseEnum<String> {

    HOUR(0, "小时"), // 特殊：字典里，暂时不会有这个枚举！！！因为大多数情况下，用不到这个间隔
    DAY(1, "天"),
    WEEK(2, "周"),
    MONTH(3, "月"),
    QUARTER(4, "季度"),
    YEAR(5, "年")
    ;

    public static final Integer[] ARRAYS = Arrays.stream(values()).map(DateIntervalEnum::getInterval).toArray(Integer[]::new);

    /**
     * 类型
     */
    private final Integer interval;
    /**
     * 名称
     */
    private final String name;


    public static DateIntervalEnum valueOf(Integer interval) {
        return ArrayUtil.firstMatch(item -> item.getInterval().equals(interval), DateIntervalEnum.values());
    }

    @Override
    public String getValue() {
        return name;
    }

    @Override
    public Integer getCode() {
        return interval;
    }
}