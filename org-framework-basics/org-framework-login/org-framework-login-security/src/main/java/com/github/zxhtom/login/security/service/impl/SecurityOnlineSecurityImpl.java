package com.github.zxhtom.login.security.service.impl;

import com.github.zxhtom.web.auths.OnlineSecurity;
import com.github.zxhtom.web.auths.ScopeStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.login.security.service.impl
 * @date 2021/9/30 10:48
 */
@Service
public class SecurityOnlineSecurityImpl implements OnlineSecurity {

    @Autowired
    ScopeStoreService scopeStoreService;

    @Override
    public Object getOnlinePrincipal() {
        SecurityContext context = SecurityContextHolder.getContext();
        if (context != null) {
            Authentication authentication = context.getAuthentication();
            if (authentication != null) {
                return authentication.getPrincipal();
            }
        }
        return new Object();
    }

    @Override
    public String getOnlineUserName() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    @Override
    public String getInterfaceName() {
        return scopeStoreService.getEntryPath();
    }

    @Override
    public boolean hasAnyRole(String... roles) {
        return SecurityContextHolder.getContext().getAuthentication().getAuthorities().contains(roles);
    }

    @Override
    public Set<String> getRoleNames() {
        Set<String> res = new HashSet<>();
        Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        for (GrantedAuthority authority : authorities) {
            res.add(authority.getAuthority());
        }
        return res;
    }
}
