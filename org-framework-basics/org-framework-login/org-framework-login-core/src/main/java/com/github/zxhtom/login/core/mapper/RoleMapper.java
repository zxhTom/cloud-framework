package com.github.zxhtom.login.core.mapper;

import com.github.zxhtom.login.core.model.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.login.security.mapper
 * @date 2021/9/30 9:57
 */
public interface RoleMapper {

    Set<String> selectPermissionsBaseOnUserName(@Param("userName") String userName);


    List<Role> selectRoleBaseButton(@Param("buttonId") Long buttonId);
}
