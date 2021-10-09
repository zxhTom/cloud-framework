package com.github.zxhtom.login.security.handler.impl;

import com.github.zxhtom.login.core.model.PermissionUrl;
import com.github.zxhtom.login.security.handler.AbastractPathAttributeHandler;
import org.springframework.security.access.ConfigAttribute;

import java.util.Collection;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.login.security.handler.impl
 * @date 2021/10/9 11:30
 */
public class NullAttributeHandler extends AbastractPathAttributeHandler {
    @Override
    public boolean doSupport(PermissionUrl permissionUrl) {
        return permissionUrl.getType()==1;
    }

    @Override
    public Collection<ConfigAttribute> handle(PermissionUrl permissionUrl) {
        return null;
    }
}
