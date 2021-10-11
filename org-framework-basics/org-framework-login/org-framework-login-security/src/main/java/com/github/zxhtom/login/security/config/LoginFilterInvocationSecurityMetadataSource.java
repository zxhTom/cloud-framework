package com.github.zxhtom.login.security.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.access.intercept.DefaultFilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.util.matcher.RequestMatcher;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.login.security.config
 * @date 2021/10/11 11:43
 */
public class LoginFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource , InitializingBean {

    @Autowired
    FilterSecurityInterceptor filterSecurityInterceptor;

    public Collection<ConfigAttribute> getAttributesFromDatabase() {
        return null;
    }

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        Collection<ConfigAttribute> attributes = filterSecurityInterceptor.getSecurityMetadataSource().getAttributes(object);
        return null;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        Collection<ConfigAttribute> allConfigAttributes = filterSecurityInterceptor.getSecurityMetadataSource().getAllConfigAttributes();
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return filterSecurityInterceptor.getSecurityMetadataSource().supports(clazz);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
    }
}
