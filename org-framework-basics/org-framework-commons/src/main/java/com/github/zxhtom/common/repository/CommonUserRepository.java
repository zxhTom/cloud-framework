package com.github.zxhtom.common.repository;

import com.github.zxhtom.login.core.model.User;

import java.util.List;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.common.repository
 * @date 2021/10/22 14:00
 */
public interface CommonUserRepository {
    List<User> selectUserAllWithAccess(Integer pageStart, Integer pageSize);

    Long selectUserCountAllWithAccess(Integer pageStart, Integer pageSize);

    List<User> selectUserWithUserIdAccess(Long userId, Integer pageStart, Integer pageSize);

    Long selectUserCountWithUserIdAccess(Long userId, Integer pageStart, Integer pageSize);

    Integer insert(User user);

    Integer update(User user);

    Integer deleteBatch(List<Long> ids);
}
