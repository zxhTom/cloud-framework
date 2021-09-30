package com.github.zxhtom.demo.login.model;

import com.github.zxhtom.core.enums.BaseEnum;
import com.github.zxhtom.demo.login.enums.SexEnum;
import lombok.Data;

import java.util.Date;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.demo.login.model
 * @date 2021/9/18 9:27
 */
@Data
public class User {
    private String userName;
    private String userNick;
    private String userCode;
    private String password;
    private String phone;
    private String email;
    private Date birthDate;
    private SexEnum sex;
}

