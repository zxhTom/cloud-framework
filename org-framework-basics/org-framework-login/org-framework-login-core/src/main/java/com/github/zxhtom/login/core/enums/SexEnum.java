package com.github.zxhtom.login.core.enums;

import com.github.zxhtom.core.enums.BaseEnum;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.login.security.enums
 * @date 2021/9/28 15:27
 */
public enum SexEnum implements BaseEnum<Integer> {
    /**
     * 男性
     */
    MALE(1, 1,"男"),
    /**
     * 女性
     */
    FEMALE(0, 0,"女");


    private Integer code;
    private Integer value;
    private String description;

    private SexEnum(Integer code, Integer value,String description) {
        this.value = value;
        this.code = code;
        this.description = description;
    }
    @Override
    public Integer getValue() {
        return value;
    }

    @Override
    public Integer getCode() {
        return code;
    }
}
