package com.github.zxhtom.login.core.model;

import com.github.zxhtom.login.core.model.Button;
import com.github.zxhtom.login.core.model.Role;
import lombok.Data;

import java.util.List;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.login.security.model
 * @date 2021/10/8 16:40
 */
@Data
public class ButtonRoleDto extends Button {

    private List<Role> roleList;
}
