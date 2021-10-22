package com.github.zxhtom.login.core.enums;

import com.github.zxhtom.core.enums.BaseEnum;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.login.core.enums
 * @date 2021/10/12 16:47
 */
public enum MenuTypeEnum implements BaseEnum<Integer> {
    REAL_MENU(0,0,"真实菜单"),
    VISUAL_MENU(1, 1, "虚拟菜单"),;

    private Integer code;
    private Integer value;
    private String description;

    private MenuTypeEnum(Integer code, Integer value,String description) {
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
