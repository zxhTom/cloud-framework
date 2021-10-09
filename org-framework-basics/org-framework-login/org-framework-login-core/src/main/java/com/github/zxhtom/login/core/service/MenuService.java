package com.github.zxhtom.login.core.service;

import com.github.zxhtom.login.core.model.Role;

import java.util.List;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.login.core.service
 * @date 2021/10/9 14:24
 */
public interface MenuService {

    List<Role> selectWithRoleBaseOnMenuUrl(String menuUrl);
}
