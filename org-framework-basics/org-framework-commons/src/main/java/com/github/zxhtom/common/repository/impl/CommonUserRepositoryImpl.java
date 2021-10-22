package com.github.zxhtom.common.repository.impl;

import com.github.zxhtom.common.mapper.CommonUserMapper;
import com.github.zxhtom.common.repository.CommonUserRepository;
import com.github.zxhtom.login.core.mapper.UserMapper;
import com.github.zxhtom.login.core.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.common.repository.impl
 * @date 2021/10/22 14:00
 */
@Repository
public class CommonUserRepositoryImpl implements CommonUserRepository {
    @Autowired
    CommonUserMapper commonUserMapper;

    @Autowired
    UserMapper userMapper;

    @Override
    public List<User> selectUserAllWithAccess(Integer pageStart, Integer pageSize) {
        List<User> users = commonUserMapper.selectUserAllWithAccess(null, pageStart, pageSize);
        users.stream().forEach(item -> {
            item.setPassword("********************");
        });
        return users;
    }

    @Override
    public Long selectUserCountAllWithAccess(Integer pageStart, Integer pageSize) {
        return commonUserMapper.selectUserCountAllWithAccess(null,pageStart, pageSize);
    }

    @Override
    public List<User> selectUserWithUserIdAccess(Long userId, Integer pageStart, Integer pageSize) {
        return commonUserMapper.selectUserAllWithAccess(userId,pageStart, pageSize);
    }

    @Override
    public Long selectUserCountWithUserIdAccess(Long userId, Integer pageStart, Integer pageSize) {
        return commonUserMapper.selectUserCountAllWithAccess(userId,pageStart, pageSize);
    }

    @Override
    public Integer insert(User user) {
        return userMapper.insertUser(user);
    }

    @Override
    public Integer update(User user) {
        return commonUserMapper.updateUser(user);
    }

    @Override
    public Integer deleteBatch(List<Long> ids) {

        return commonUserMapper.deleteBatch(ids);
    }
}
