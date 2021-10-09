package com.github.zxhtom.login.core.service;

import com.github.zxhtom.login.core.model.User;
import org.apache.ibatis.annotations.Param;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Set;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.login.security.service
 * @date 2021/9/29 10:28
 */
public interface UserService {
    public List<User> selectUserList();

    public User selectUserBaseOnUserName(@Param("userName") String userName);

    public Integer insertUser(User user);

    Set<String> selectPermissionsBaseOnUserName(String userName);
}
