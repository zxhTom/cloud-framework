package com.github.zxhtom.login.security.service;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.login.security.service
 * @date 2021/9/15 15:34
 */
import java.util.Set;

import com.github.zxhtom.login.security.model.User;

/**
 * 用户管理
 * @author Louis
 * @date Nov 28, 2018
 */
public interface UserService {

    /**
     * 根据用户名查找用户
     * @param username
     * @return
     */
    User findByUsername(String username);

    /**
     * 查找用户的菜单权限标识集合
     * @return
     */
    Set<String> findPermissions(String username);

}
