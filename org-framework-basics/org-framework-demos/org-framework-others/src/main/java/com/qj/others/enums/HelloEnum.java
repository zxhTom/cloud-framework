package com.qj.others.enums;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.demo.enums
 * @date 2021/8/26 17:25
 */
public enum HelloEnum {
    left("合格"),
    right("不合格"),
    ;
    private String name;

    HelloEnum(String name) {
        this.name = name;
    }
}
