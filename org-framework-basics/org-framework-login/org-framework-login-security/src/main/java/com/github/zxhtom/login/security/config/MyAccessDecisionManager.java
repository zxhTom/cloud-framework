package com.github.zxhtom.login.security.config;

import com.github.zxhtom.core.constant.MaltcloudConstant;
import com.github.zxhtom.web.context.HttpUtils;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Iterator;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.demo.login.config
 * @date 2021/10/8 14:00
 */
@Component
public class MyAccessDecisionManager implements AccessDecisionManager {
    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
        if(null== configAttributes || configAttributes.size() <=0) {
            return;
        }
        ConfigAttribute c;
        String needRole;
        for(Iterator<ConfigAttribute> iter = configAttributes.iterator(); iter.hasNext(); ) {
            c = iter.next();
            needRole = c.getAttribute();
            for(GrantedAuthority ga : authentication.getAuthorities()) {//authentication 为在注释1 中循环添加到 GrantedAuthority 对象中的权限信息集合
                if(needRole.trim().equals(ga.getAuthority())) {
                    return;
                }
            }
            if (MaltcloudConstant.LOGIN_ROLE.equals(needRole)) {
                throw new AccessDeniedException("该接口需要登录，请先登录Please!!!但是请原谅我无法为您原生登录提示");
            }
        }
        throw new AccessDeniedException("无权限访问");
    }

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
