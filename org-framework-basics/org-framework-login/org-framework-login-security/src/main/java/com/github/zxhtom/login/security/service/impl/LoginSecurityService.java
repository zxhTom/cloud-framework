package com.github.zxhtom.login.security.service.impl;

import com.github.zxhtom.login.core.service.LoginService;
import com.github.zxhtom.login.security.utils.SecurityUtils;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.login.security.service.impl
 * @date 2021/10/9 10:00
 */
public class LoginSecurityService implements LoginService {

    @Override
    public Integer refreshRoles(String userName) {
        SecurityUtils.reloadUserAuthority(userName);
        return 1;
    }
}
