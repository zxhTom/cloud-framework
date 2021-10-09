package com.github.zxhtom.login.core.model;

import com.github.zxhtom.datasource.model.BaseModel;
import lombok.Data;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.login.core.model
 * @date 2021/10/8 14:35
 */
@Data
public class Button extends BaseModel {
    private Long id;
    private Long moduleId;
    private Long menuId;
    private Integer buttonCode;
    private String buttonName;
    private String buttonIcon;
    private Integer type;
    private String url;
}
