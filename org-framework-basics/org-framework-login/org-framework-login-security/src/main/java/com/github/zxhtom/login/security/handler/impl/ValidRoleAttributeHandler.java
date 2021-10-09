package com.github.zxhtom.login.security.handler.impl;

import com.github.zxhtom.login.core.model.PermissionUrl;
import com.github.zxhtom.login.core.model.PermissionUrlRoleDto;
import com.github.zxhtom.login.core.service.PermissionUrlService;
import com.github.zxhtom.login.security.handler.AbastractPathAttributeHandler;
import com.github.zxhtom.web.context.ApplicationContextUtil;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.login.security.handler.impl
 * @date 2021/10/9 11:31
 */
public class ValidRoleAttributeHandler extends AbastractPathAttributeHandler{

    @Override
    public boolean doSupport(PermissionUrl permissionUrl) {
        return permissionUrl.getType() == 2;
    }

    @Override
    public Collection<ConfigAttribute> handle(PermissionUrl permissionUrl) {
        PermissionUrlService permissionUrlService = ApplicationContextUtil.getApplicationContext().getBean(PermissionUrlService.class);
        //验证权限
        List<PermissionUrlRoleDto> roleList = permissionUrlService.selectRoleBaseOnPermissionUrl(permissionUrl.getId());
        if (CollectionUtils.isNotEmpty(roleList)) {
            Collection<ConfigAttribute> collection = new ArrayList<>();
            for (PermissionUrlRoleDto permissionUrlRole : roleList) {
                collection.add(new SecurityConfig(permissionUrlRole.getName()));
            }
            return collection;
        }
        throw new AccessDeniedException("配置全家请求权限发生异常,禁止访问！！！");
    }
}
