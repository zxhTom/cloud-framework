package com.github.zxhtom.login.core.service;

import com.github.zxhtom.login.core.model.Button;
import com.github.zxhtom.login.core.model.ButtonRoleDto;

import java.util.List;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.login.security.service
 * @date 2021/10/8 14:38
 */
public interface ButtonService {
    public List<Button> selectList();

    public List<ButtonRoleDto> selectButtonMasterAndRoleListBaseBName(String buttonUrl);
}
