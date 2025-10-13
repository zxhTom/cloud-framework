package com.github.zxhtom.login.core.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.mapper.Mapper;
import com.github.zxhtom.login.core.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.login.security.mapper
 * @date 2021/9/28 15:34
 */
public interface UserMapper extends BaseMapper<User> {

    public List<User> selectUserList();

    public User selectUserBaseOnUserName(@Param("userName") String userName);

    public User selectUserBaseOnUserId(@Param("userId") Long userId);

    public Integer insertUser(User user);

}
