package com.github.zxhtom.login.core.repository.impl;

import com.github.zxhtom.login.core.mapper.UserMapper;
import com.github.zxhtom.login.core.model.User;
import com.github.zxhtom.login.core.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.login.core.repository.impl
 * @date 2021/10/12 16:22
 */
@Repository
public class UserRepositoryImpl implements UserRepository {
    @Autowired
    UserMapper userMapper;

    @Override
    public User findByUsername(String userName) {
        User user = userMapper.selectUserBaseOnUserName(userName);
        return user;
    }

    @Override
    public boolean existsByUserName(String userName) {
        User user = findByUsername(userName);
        return user!=null;
    }

    @Override
    public Integer insert(User user) {
        return userMapper.insertUser(user);
    }
}
