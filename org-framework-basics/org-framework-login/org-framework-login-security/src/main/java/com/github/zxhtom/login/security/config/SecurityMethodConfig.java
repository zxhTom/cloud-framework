package com.github.zxhtom.login.security.config;

import org.aopalliance.intercept.MethodInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.annotation.Jsr250Voter;
import org.springframework.security.access.expression.AbstractSecurityExpressionHandler;
import org.springframework.security.access.intercept.aopalliance.MethodSecurityInterceptor;
import org.springframework.security.access.method.DelegatingMethodSecurityMetadataSource;
import org.springframework.security.access.method.MethodSecurityMetadataSource;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.access.vote.AuthenticatedVoter;
import org.springframework.security.access.vote.RoleVoter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.access.expression.WebExpressionVoter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Configuration
@EnableGlobalMethodSecurity(
        prePostEnabled = true,
        securedEnabled = true,
        jsr250Enabled = true
)
@Order(3)
public class SecurityMethodConfig extends GlobalMethodSecurityConfiguration {
    @Autowired
    @Lazy
    private AuthenticationManager authenticationManager;

    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return authenticationManager;
    }
    private static final Logger logger = LoggerFactory.getLogger(SecurityMethodConfig.class);

    @Bean
    @Override
    @Primary
    public MethodInterceptor methodSecurityInterceptor(MethodSecurityMetadataSource methodSecurityMetadataSource) {
        logger.info(">>> 自定义 methodSecurityInterceptor 创建开始");

        // 调用父类方法获取基础的拦截器
        MethodSecurityInterceptor interceptor = (MethodSecurityInterceptor)
                super.methodSecurityInterceptor(methodSecurityMetadataSource);

        // 进行自定义配置
        customizeMethodSecurityInterceptor(interceptor);

        logger.info("<<< 自定义 methodSecurityInterceptor 创建完成");
        return interceptor;
    }

    private void customizeMethodSecurityInterceptor(MethodSecurityInterceptor interceptor) {
        // 1. 自定义 AccessDecisionManager
        interceptor.setAccessDecisionManager(customAccessDecisionManager());

        // 2. 自定义 SecurityMetadataSource（如果需要）
        // interceptor.setSecurityMetadataSource(customMethodSecurityMetadataSource());

        // 3. 自定义 RunAsManager（如果需要）
        // interceptor.setRunAsManager(customRunAsManager());

        // 4. 自定义其他属性
        interceptor.setPublishAuthorizationSuccess(true); // 发布授权成功事件
        interceptor.setValidateConfigAttributes(true);    // 验证配置属性

        logger.info("✅ MethodSecurityInterceptor 自定义完成");
    }

    @Bean
    public AccessDecisionManager customAccessDecisionManager() {
        logger.info(">>> 创建自定义 AccessDecisionManager");

        List<AccessDecisionVoter<?>> voters = new ArrayList<>();
        // 1. Web 表达式 Voter（处理 access() 表达式）
//        voters.add(webExpressionVoter());
        // 添加 JSR-250 Voter（必须）
        voters.add(new Jsr250Voter());

        // 添加角色 Voter
        voters.add(new RoleVoter());

        // 添加认证状态 Voter
        voters.add(new AuthenticatedVoter());

        // 添加自定义 Voter（可选）
        voters.add(customAccessDecisionVoter());

        // 使用肯定基于的决策管理器
        AffirmativeBased decisionManager = new AffirmativeBased(voters);

        logger.info("<<< 自定义 AccessDecisionManager 创建完成");
        return decisionManager;
    }
    @Bean
    public WebExpressionVoter webExpressionVoter() {
        WebExpressionVoter voter = new WebExpressionVoter();
        voter.setExpressionHandler(webSecurityExpressionHandler());
        return voter;
    }

    @Bean
    public AbstractSecurityExpressionHandler webSecurityExpressionHandler() {
        DefaultWebSecurityExpressionHandler handler = new DefaultWebSecurityExpressionHandler();
        // 可以自定义表达式处理器
        // handler.setPermissionEvaluator(customPermissionEvaluator());
        // handler.setRoleHierarchy(roleHierarchy());
        return handler;
    }
    @Bean
    public AccessDecisionVoter<Object> customAccessDecisionVoter() {
        return new AccessDecisionVoter<Object>() {
            @Override
            public boolean supports(ConfigAttribute attribute) {
                // 支持特定的配置属性
                return attribute.getAttribute() != null &&
                        attribute.getAttribute().startsWith("CUSTOM_");
            }

            @Override
            public boolean supports(Class<?> clazz) {
                return true;
            }

            @Override
            public int vote(Authentication authentication, Object object,
                            Collection<ConfigAttribute> attributes) {
                // 自定义投票逻辑
                return ACCESS_ABSTAIN;
            }
        };
    }

    // 也可以重写其他相关方法
    @Bean
    @Override
    public MethodSecurityMetadataSource methodSecurityMetadataSource() {
        logger.info(">>> 创建自定义 MethodSecurityMetadataSource");

        // 调用父类获取默认的委托源
        DelegatingMethodSecurityMetadataSource defaultSource =
                (DelegatingMethodSecurityMetadataSource) super.methodSecurityMetadataSource();

        // 可以进行自定义包装或替换
        return defaultSource;
    }
}
