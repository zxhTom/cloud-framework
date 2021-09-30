package com.github.zxhtom.login.core.model;

import com.github.zxhtom.datasource.model.BaseModel;
import lombok.Data;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.login.security.model
 * @date 2021/9/28 15:32
 */
@Data
public class Role extends BaseModel {
    private Long id;
    private Integer code;
    private String name;
    private Long parentId;
}
