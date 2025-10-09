package com.github.zxhtom.login.core.service.impl;

import com.github.zxhtom.login.core.mapper.RoleMapper;
import com.github.zxhtom.login.core.model.Role;
import com.github.zxhtom.login.core.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.login.core.service.impl
 * @date 2021/10/12 16:28
 */
@Repository
public class RoleRepositoryImpl implements RoleRepository {
    @Autowired
    RoleMapper roleMapper;
    @Override
    public List<Role> selectRolesByUserId(Long userId) {
        return roleMapper.selectRoleBaseUser(userId);
    }
}
