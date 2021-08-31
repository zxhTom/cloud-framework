package com.github.zxhtom.demo.enums;

import com.baomidou.mybatisplus.annotation.IEnum;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.demo.enums
 * @date 2021/8/26 17:11
 */
public enum SexEnum implements IEnum<Integer> {
    MALE(1, "男"),
    FEMALE(0, "女");

    private int value;
    private String desc;

    SexEnum(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }
    @Override
    public Integer getValue() {
        return this.value;
    }
    @Override
    public String toString() {
        return this.desc;
    }
}
