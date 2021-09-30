package com.github.zxhtom.demo.login.enums;

import com.github.zxhtom.core.enums.BaseEnum;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.demo.login.enums
 * @date 2021/9/28 17:29
 */
public enum SexEnum implements BaseEnum<Integer> {
    /**
     * 失败
     */
    FAILURE(1, 1),
    /**
     * 成功
     */
    SUCCESS(0, 0);


    private Integer code;
    private Integer value;

    private SexEnum(Integer code, Integer value) {
        this.value = value;
        this.code = code;
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
