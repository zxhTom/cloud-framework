package com.github.zxhtom.login.core.model;

import com.github.zxhtom.datasource.model.BaseModel;
import lombok.Data;

/**
 * TODO
 *
 * @author zxhtom
 * 10/9/25
 */
@Data
public class MiniUser extends BaseModel {
    private Long id;
    private String appId;
    private String openId;
    private Long userId;
}
