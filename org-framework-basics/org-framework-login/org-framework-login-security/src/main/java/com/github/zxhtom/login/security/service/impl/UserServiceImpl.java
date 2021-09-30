package com.github.zxhtom.login.security.service.impl;

import com.github.zxhtom.core.datasouce.FillDataHandler;
import com.github.zxhtom.login.core.model.User;
import com.github.zxhtom.login.security.mapper.RoleMapper;
import com.github.zxhtom.login.security.mapper.UserMapper;
import com.github.zxhtom.login.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

    @Autowired
    FillDataHandler fillDataHandler;

    @Override
    public List<User> selectUserList() {
        return userMapper.selectUserList();
    }

    @Override
    public User selectUserBaseOnUserName(String userName) {
        return userMapper.selectUserBaseOnUserName(userName);
    }

    @Override
    public Integer insertUser(User user) throws InvocationTargetException, IllegalAccessException {
        user.setPassword(new BCryptPasswordEncoder().encode("123456"));
        return userMapper.insertUser(user);
    }

    @Override
    public Set<String> selectPermissionsBaseOnUserName(String userName) {
        return roleMapper.selectPermissionsBaseOnUserName(userName);
    }
}
