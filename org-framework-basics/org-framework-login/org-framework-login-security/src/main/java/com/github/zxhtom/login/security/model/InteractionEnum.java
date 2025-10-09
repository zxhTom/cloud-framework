package com.github.zxhtom.login.security.model;

import com.baomidou.mybatisplus.annotation.IEnum;

public enum InteractionEnum implements IEnum<String> {
    JSON("json","JSON payload interaction with front"),
    VIEW("view","view template interaction with front");

    private String value;
    private String desc;

    InteractionEnum(String value, String desc) {
        this.value = value;
        this.desc = desc;
    }
    @Override
    public String getValue() {
        return this.value;
    }
}
