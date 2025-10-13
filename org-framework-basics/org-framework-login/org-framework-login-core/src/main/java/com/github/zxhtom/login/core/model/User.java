package com.github.zxhtom.login.core.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.github.zxhtom.datasource.model.BaseModel;
import com.github.zxhtom.login.core.enums.SexEnum;
import lombok.Data;

import java.util.Date;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.login.security.model
 * @date 2021/9/28 15:30
 */
@Data
@TableName("users")
public class User extends BaseModel {
    private Long id;
    private String userName;
    private String realName;
    private String userNick;
    private Integer userCode;
    private String password;
    private String phone;
    private String email;
    private Date birthDate;
    private SexEnum sex;
    private String headImg;
    private String specialSign;
}
