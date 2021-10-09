package com.github.zxhtom.login.core.service;

import com.github.zxhtom.login.core.model.PermissionUrl;
import com.github.zxhtom.login.core.model.PermissionUrlRole;
import com.github.zxhtom.login.core.model.PermissionUrlRoleDto;

import java.util.List;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.login.core.service
 * @date 2021/10/9 10:59
 */
public interface PermissionUrlService {

    List<PermissionUrl> selectAllPermissionUrls();

    List<PermissionUrlRoleDto> selectRoleBaseOnPermissionUrl(Long id);
}
