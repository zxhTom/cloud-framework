package com.github.zxhtom.mini.dto;

import com.github.zxhtom.login.core.model.User;
import lombok.Data;

/**
 * @author zxhtom
 * 10/12/25
 */
@Data
public class ContractUser extends User {
    private String appId;
}
