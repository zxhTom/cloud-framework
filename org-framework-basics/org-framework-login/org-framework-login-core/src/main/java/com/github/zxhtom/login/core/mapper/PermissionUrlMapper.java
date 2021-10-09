package com.github.zxhtom.login.core.mapper;

import com.github.zxhtom.login.core.model.PermissionUrl;
import com.github.zxhtom.login.core.model.PermissionUrlRole;
import com.github.zxhtom.login.core.model.PermissionUrlRoleDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.login.core.mapper
 * @date 2021/10/9 11:01
 */
public interface PermissionUrlMapper {
    List<PermissionUrl> selectAllPermissionUrls();

    List<PermissionUrlRoleDto> selectRoleBaseOnPermissionUrl(@Param("permissionId") Long permissionId);
}
