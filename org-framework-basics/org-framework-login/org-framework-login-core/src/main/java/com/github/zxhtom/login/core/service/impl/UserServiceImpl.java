package com.github.zxhtom.login.core.service.impl;

import com.github.zxhtom.login.core.mapper.RoleMapper;
import com.github.zxhtom.login.core.mapper.UserMapper;
import com.github.zxhtom.login.core.model.User;
import com.github.zxhtom.login.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Set;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.login.security.service.impl
 * @date 2021/9/29 10:28
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<User> selectUserList() {
        return userMapper.selectUserList();
    }

    @Override
    public User selectUserBaseOnUserName(String userName) {
        return userMapper.selectUserBaseOnUserName(userName);
    }

    @Override
    public Integer insertUser(User user){
        return userMapper.insertUser(user);
    }

    @Override
    public Set<String> selectPermissionsBaseOnUserName(String userName) {
        return roleMapper.selectPermissionsBaseOnUserName(userName);
    }
}
