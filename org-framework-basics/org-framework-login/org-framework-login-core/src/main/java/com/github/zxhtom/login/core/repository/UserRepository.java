package com.github.zxhtom.login.core.repository;

import com.github.zxhtom.login.core.model.User;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.login.core.repository
 * @date 2021/10/12 16:21
 */
public interface UserRepository {
    User findByUsername(String userName);

    boolean existsByUserName(String userName);

    Integer insert(User user);

    User selectByUserId(Long userId);
}
