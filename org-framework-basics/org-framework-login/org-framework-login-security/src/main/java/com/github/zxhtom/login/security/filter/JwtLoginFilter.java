package com.github.zxhtom.login.security.filter;

import com.github.zxhtom.login.security.token.JwtAuthenticatioToken;
import com.github.zxhtom.login.security.utils.JwtTokenUtils;
import com.github.zxhtom.web.context.HttpUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.login.security.filter
 * @date 2021/9/27 11:16
 * @description 用于认证jwt登录
 */
/*
@Component
public class JwtLoginFilter extends UsernamePasswordAuthenticationFilter {
    @Autowired(required = false)
    JwtTokenUtils jwtTokenUtils;
    @Override
    protected void initFilterBean() throws ServletException {
        if (jwtTokenUtils == null) {
            throw new ServletException("JwtTokenUtil must not be null");
        }
    }
    public JwtLoginFilter(AuthenticationManager authManager) {
        setAuthenticationManager(authManager);
        super.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/jwtLogin", "POST"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        String userName = request.getParameter("userName");
        String password = obtainPassword(request);
        if (StringUtils.isEmpty(userName)) {
            userName = StringUtils.EMPTY;
        }
        if (StringUtils.isEmpty(password)) {
            password = StringUtils.EMPTY;
        }
        userName = userName.trim();
        JwtAuthenticatioToken authRequest = new JwtAuthenticatioToken(userName, password);
        setDetails(request, authRequest);
        return this.getAuthenticationManager().authenticate(authRequest);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        // 存储登录认证信息到上下文
        SecurityContextHolder.getContext().setAuthentication(authResult);
        // 记住我服务
        getRememberMeServices().loginSuccess(request, response, authResult);
        // 触发事件监听器
        if (this.eventPublisher != null) {
            eventPublisher.publishEvent(new InteractiveAuthenticationSuccessEvent(authResult, this.getClass()));
        }
        // 生成并返回token给客户端，后续访问携带此token
        JwtAuthenticatioToken token = new JwtAuthenticatioToken(null, null, jwtTokenUtils.generateToken(authResult));
        HttpUtils.write(response, token);
    }

}
*/
