package com.github.zxhtom.login.core.model;

import lombok.Data;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.login.core.model
 * @date 2021/10/9 14:41
 */
@Data
public class Module {
    private Long id;
    private String code;
    private String name;
    private String url;
    private String icon;
}
