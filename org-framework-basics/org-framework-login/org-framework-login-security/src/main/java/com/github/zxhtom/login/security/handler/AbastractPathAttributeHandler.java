package com.github.zxhtom.login.security.handler;

import com.github.zxhtom.login.core.model.PermissionUrl;
import org.springframework.security.access.ConfigAttribute;

import java.util.Collection;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.login.security.handler
 * @date 2021/10/9 11:23
 */
public abstract class AbastractPathAttributeHandler {

    public boolean support(PermissionUrl permissionUrl) {
        return doSupport(permissionUrl);
    }

    public abstract boolean doSupport(PermissionUrl permissionUrl);

    public abstract Collection<ConfigAttribute> handle(PermissionUrl permissionUrl);
}
