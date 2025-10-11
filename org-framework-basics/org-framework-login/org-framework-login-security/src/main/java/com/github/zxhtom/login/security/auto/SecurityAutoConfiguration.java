package com.github.zxhtom.login.security.auto;

import com.github.zxhtom.login.core.autoconfige.LoginCoreAutoConfiguration;
import com.github.zxhtom.login.core.service.LoginService;
import com.github.zxhtom.login.security.config.*;
import com.github.zxhtom.login.security.filter.JwtCloudAuthenticationFilter;
import com.github.zxhtom.login.security.handler.RoleUrlHandler;
import com.github.zxhtom.login.security.handler.chain.ButtonRoleHandler;
import com.github.zxhtom.login.security.handler.chain.MenuRoleHandler;
import com.github.zxhtom.login.security.handler.chain.ModuleRoleHandler;
import com.github.zxhtom.login.security.handler.impl.NullAttributeHandler;
import com.github.zxhtom.login.security.handler.impl.ValidRoleAttributeHandler;
import com.github.zxhtom.login.security.service.impl.LoginSecurityService;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.method.MethodSecurityMetadataSource;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.login.security.auto
 * @date 2021/10/9 10:01
 */
@Configuration
@AutoConfigureBefore(LoginCoreAutoConfiguration.class)
public class SecurityAutoConfiguration {

    @Bean
    public LoginService loginService() {
        return new LoginSecurityService();
    }

    @Bean
    @ConditionalOnMissingBean(FilterInvocationSecurityMetadataSource.class)
    public MyInvocationSecurityMetadataSourceService filterInvocationSecurityMetadataSource(){
        MyInvocationSecurityMetadataSourceService metadataSouceService = new MyInvocationSecurityMetadataSourceService();
        metadataSouceService.registerHandler(new NullAttributeHandler());
        metadataSouceService.registerHandler(new ValidRoleAttributeHandler());
        return metadataSouceService;
    }
    @Bean
    @ConditionalOnMissingBean(MyAccessDecisionManager.class)
    public MyAccessDecisionManager myAccessDecisionManager(){
        return new MyAccessDecisionManager();
    }

    @Bean
    @ConditionalOnMissingBean(MyFilterSecurityInterceptor.class)
    public MyFilterSecurityInterceptor myFilterSecurityInterceptor() {
        return new MyFilterSecurityInterceptor();
    }

    //@Bean
    //@ConditionalOnMissingBean(LoginFilterSecurityInterceptor.class)
    public LoginFilterSecurityInterceptor loginFilterSecurityInterceptor() {
        LoginFilterSecurityInterceptor interceptor = new LoginFilterSecurityInterceptor();
        interceptor.setSecurityMetadataSource(new LoginFilterInvocationSecurityMetadataSource());
        return interceptor;
    }
    @Bean
    @ConditionalOnMissingBean(ButtonRoleHandler.class)
    public RoleUrlHandler buttonRoleHandler() {
        return new ButtonRoleHandler();
    }

    @Bean
    @ConditionalOnMissingBean(MenuRoleHandler.class)
    public RoleUrlHandler menuRoleHandler() {
        return new MenuRoleHandler();
    }

    @Bean
    @ConditionalOnMissingBean(ModuleRoleHandler.class)
    public RoleUrlHandler moduleRoleHandler() {
        return new ModuleRoleHandler();
    }
    @Bean
    @ConditionalOnMissingBean
    public JwtCloudAuthenticationFilter jwtCloudAuthenticationFilter() {
        return new JwtCloudAuthenticationFilter();
    }
    @Bean
    public FilterRegistrationBean<JwtCloudAuthenticationFilter> disableJwtFilterRegistration(JwtCloudAuthenticationFilter filter) {
        FilterRegistrationBean<JwtCloudAuthenticationFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(filter);
        registration.setEnabled(false);
        return registration;
    }
/*
    @Bean
    public MethodFirstSecurityMetadataSource methodFirstSecurityMetadataSource(
            MethodSecurityMetadataSource methodSecurityMetadataSource,
            HttpSecurity httpSecurity) {
        try {
            return new MethodFirstSecurityMetadataSource(methodSecurityMetadataSource,httpSecurity);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
*/
}
