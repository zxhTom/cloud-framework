package com.github.zxhtom.core.model;

import lombok.Data;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.core.model
 * @date 2021/9/29 11:50
 */
@Data
public class FillDataClassMapModel<T , E extends T> {
    String name;
    Class<T> ct;
    E e;

    public FillDataClassMapModel(String name, Class<T> ct, E e) {
        this.name = name;
        this.ct=ct;
        this.e=e;
    }
}
