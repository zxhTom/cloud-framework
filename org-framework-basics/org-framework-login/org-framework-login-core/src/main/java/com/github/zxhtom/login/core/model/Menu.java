package com.github.zxhtom.login.core.model;

import com.github.zxhtom.datasource.model.BaseModel;
import com.github.zxhtom.login.core.enums.MenuTypeEnum;
import lombok.Data;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.login.core.model
 * @date 2021/10/9 14:40
 */
@Data
public class Menu extends BaseModel {
    private Long id;
    private String code;
    private String name;
    private String url;
    private Long parentId;
    private String icon;
    private Integer orderIndex;
    private Integer menuType;

}
