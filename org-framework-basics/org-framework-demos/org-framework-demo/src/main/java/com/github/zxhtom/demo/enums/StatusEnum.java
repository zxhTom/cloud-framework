package com.github.zxhtom.demo.enums;

import com.github.zxhtom.core.enums.BaseEnum;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.demo.enums
 * @date 2021/9/8 15:11
 */
public enum StatusEnum implements BaseEnum<Integer> {

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

    private StatusEnum(Integer code, Integer value) {
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
