package com.github.zxhtom.login.security.config;

import com.github.zxhtom.web.context.ApplicationContextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.method.MethodSecurityMetadataSource;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.DefaultFilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExecutionChain;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Set;

@Component
public class MethodFirstSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    private final FilterInvocationSecurityMetadataSource urlMetadataSource;
    private final MethodSecurityMetadataSource methodMetadataSource;
    @Autowired
    private HandlerMapping handlerMapping;

/*
    @Autowired
    public MethodFirstSecurityMetadataSource(
            MethodSecurityMetadataSource methodMetadataSource,
            HttpSecurity  httpSecurity) throws Exception {
        this.methodMetadataSource = methodMetadataSource;
        this.urlMetadataSource = createDefaultMetadataSource(httpSecurity);
    }
*/

    @Autowired
    public MethodFirstSecurityMetadataSource(
            MethodSecurityMetadataSource methodMetadataSource,
            FilterInvocationSecurityMetadataSource  filterInvocationSecurityMetadataSource) {
        this.methodMetadataSource = methodMetadataSource;
        this.urlMetadataSource = filterInvocationSecurityMetadataSource;
    }
    private FilterInvocationSecurityMetadataSource createDefaultMetadataSource(HttpSecurity http) throws Exception {
        // 创建默认的 ExpressionBasedFilterInvocationSecurityMetadataSource
        ExpressionUrlAuthorizationConfigurer<?> authConfig =
            http.getConfigurer(ExpressionUrlAuthorizationConfigurer.class);

        if (authConfig != null) {
            // 通过反射获取配置的元数据源
            Field metadataSourceField = ExpressionUrlAuthorizationConfigurer.class
                .getDeclaredField("metadataSource");
            metadataSourceField.setAccessible(true);
            return (FilterInvocationSecurityMetadataSource) metadataSourceField.get(authConfig);
        }

        // 回退方案：创建一个默认的
        return new DefaultFilterInvocationSecurityMetadataSource(
            new LinkedHashMap<RequestMatcher, Collection<ConfigAttribute>>()
        );
    }
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) {
        if (!(object instanceof FilterInvocation)) {
            return null;
        }

        FilterInvocation fi = (FilterInvocation) object;
        HttpServletRequest request = fi.getRequest();

        // 1. 先执行方法安全检查
        HandlerMethod handlerMethod = getHandlerMethod(request);
        if (handlerMethod != null) {
            Collection<ConfigAttribute> methodAttributes = methodMetadataSource.getAttributes(
                    handlerMethod.getMethod(), handlerMethod.getBeanType());

            if (methodAttributes != null && !methodAttributes.isEmpty()) {
                // 如果方法有安全注解，优先使用方法安全配置
                return methodAttributes;
            }
        }

        // 2. 方法安全没有配置，回退到URL安全
        return urlMetadataSource.getAttributes(object);
    }

    private HandlerMethod getHandlerMethod(HttpServletRequest request) {
        if (handlerMapping == null) {
            handlerMapping = ApplicationContextUtil.getApplicationContext().getBean(HandlerMapping.class);
        }
        if (handlerMapping == null) {
            return null;
        }
        try {
             // 方法2：通过 HandlerMapping 获取
            HandlerExecutionChain handlerChain = handlerMapping.getHandler(request);
            if (handlerChain != null && handlerChain.getHandler() instanceof HandlerMethod) {
                return (HandlerMethod) handlerChain.getHandler();
            }
            Object handler = request.getAttribute(HandlerMapping.BEST_MATCHING_HANDLER_ATTRIBUTE);
            return (handler instanceof HandlerMethod) ? (HandlerMethod) handler : null;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        Set<ConfigAttribute> allAttributes = new HashSet<>();
        if (urlMetadataSource.getAllConfigAttributes() != null) {
            allAttributes.addAll(urlMetadataSource.getAllConfigAttributes());
        }
        if (methodMetadataSource.getAllConfigAttributes() != null) {
            allAttributes.addAll(methodMetadataSource.getAllConfigAttributes());
        }
        return allAttributes;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return FilterInvocation.class.isAssignableFrom(clazz);
    }
}
