package com.github.zxhtom.demo.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.github.zxhtom.datasource.model.BaseModel;
import lombok.Data;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.demo.model
 * @date 2021/8/27 10:18
 */
@Data
public class TestParent extends BaseModel {
    @TableField(exist = false)
    private String userName;
}
