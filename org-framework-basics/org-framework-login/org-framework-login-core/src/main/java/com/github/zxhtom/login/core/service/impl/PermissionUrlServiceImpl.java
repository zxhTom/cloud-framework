package com.github.zxhtom.login.core.service.impl;

import com.github.zxhtom.login.core.mapper.PermissionUrlMapper;
import com.github.zxhtom.login.core.model.PermissionUrl;
import com.github.zxhtom.login.core.model.PermissionUrlRole;
import com.github.zxhtom.login.core.model.PermissionUrlRoleDto;
import com.github.zxhtom.login.core.service.PermissionUrlService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.login.core.service.impl
 * @date 2021/10/9 11:01
 */
public class PermissionUrlServiceImpl implements PermissionUrlService {
    @Autowired
    PermissionUrlMapper permissionUrlMapper;
    @Override
    public List<PermissionUrl> selectAllPermissionUrls() {
        List<PermissionUrl> permissionUrls = permissionUrlMapper.selectAllPermissionUrls();
        if (CollectionUtils.isNotEmpty(permissionUrls)) {
            return permissionUrls;
        }
        return new ArrayList<>();
    }

    @Override
    public List<PermissionUrlRoleDto> selectRoleBaseOnPermissionUrl(Long id) {
        return permissionUrlMapper.selectRoleBaseOnPermissionUrl(id);
    }
}
