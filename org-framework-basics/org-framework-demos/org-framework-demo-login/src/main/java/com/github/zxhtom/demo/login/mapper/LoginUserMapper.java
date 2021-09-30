package com.github.zxhtom.demo.login.mapper;

import com.github.zxhtom.demo.login.model.User;
import com.github.zxhtom.demo.login.model.UserMp;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.demo.login.mapper
 * @date 2021/9/28 17:27
 */
@Repository
public interface LoginUserMapper {
    public List<User> selectUserList();
    public List<User> selectTwoUser(@Param("user") User user , @Param("userMp") UserMp userMp);
}
