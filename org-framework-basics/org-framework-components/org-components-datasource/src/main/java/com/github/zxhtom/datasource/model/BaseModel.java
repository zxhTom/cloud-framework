package com.github.zxhtom.datasource.model;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.Version;
import lombok.Data;

import java.util.Date;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.datasource.model
 * @date 2021/8/27 9:53
 * @description maltcloud系统内置字段，方便管理数据权限；maltcloud内置系统统一使用此基类
 * 第三方系统强烈建议继承此类
 */
@Data
public class BaseModel {

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    @TableLogic(value = "0", delval = "1")
    @TableField(fill = FieldFill.INSERT)
    private Integer deleteFlag;
    @TableField(fill = FieldFill.INSERT)
    private String owner;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String modifer;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String interfaceEntry;
    @Version
    @TableField(fill = FieldFill.INSERT)
    private Integer version;
}
