package com.github.zxhtom.login.core.model;

import com.github.zxhtom.datasource.model.BaseModel;
import lombok.Data;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.login.core.model
 * @date 2021/10/9 11:00
 */
@Data
public class PermissionUrl extends BaseModel {
    private Long id;
    private String urlPattern;
    private Integer type;
    private String name;
}
