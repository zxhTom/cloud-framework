package com.github.zxhtom.common.mapper;

import com.github.zxhtom.login.core.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.common.mapper
 * @date 2021/10/22 14:02
 */
public interface CommonUserMapper {

    List<User> selectUserAllWithAccess(@Param("userId")Long userId , @Param("pageStart") Integer pageStart, @Param("pageSize") Integer pageSize);

    Long selectUserCountAllWithAccess(@Param("userId")Long userId , @Param("pageStart") Integer pageStart, @Param("pageSize") Integer pageSize);

    Integer updateUser(User user);

    Integer deleteBatch(@Param("ids") List<Long> ids);

}
