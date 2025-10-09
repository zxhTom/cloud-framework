package com.github.zxhtom.login.core.repository;

import com.github.zxhtom.login.core.model.Role;

import java.util.List;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.login.core.repository
 * @date 2021/10/12 16:27
 */
public interface RoleRepository {
    List<Role> selectRolesByUserId(Long userId);
}
