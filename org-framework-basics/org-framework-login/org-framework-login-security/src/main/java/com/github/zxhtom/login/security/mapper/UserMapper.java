package com.github.zxhtom.login.security.mapper;

import com.github.zxhtom.login.core.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.login.security.mapper
 * @date 2021/9/28 15:34
 */
public interface UserMapper {

    public List<User> selectUserList();

    public User selectUserBaseOnUserName(@Param("userName") String userName);

    public Integer insertUser(User user);

}
