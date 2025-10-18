package com.github.zxhtom.login.security.filter;

import cn.hutool.core.util.StrUtil;
import com.github.zxhtom.login.core.dto.LoginUser;
import com.github.zxhtom.login.core.oauth2.dto.OAuth2AccessTokenCheckRespDTO;
import com.github.zxhtom.login.core.oauth2.service.OAuth2TokenCommonApi;
import com.github.zxhtom.login.security.model.SecurityProperties;
import com.github.zxhtom.login.security.utils.JwtTokenUtils;
import com.github.zxhtom.login.security.utils.SecurityFrameworkUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtCloudAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    @Lazy
    private JwtTokenUtils jwtTokenUtils;
    @Autowired
    @Lazy
    private UserDetailsService userDetailsService;
    @Autowired(required = false)
    SecurityProperties securityProperties;
    @Autowired
    OAuth2TokenCommonApi oauth2TokenApi;
    // 构造器注入

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String token = SecurityFrameworkUtils.obtainAuthorization(request,
                securityProperties.getTokenHeader(), securityProperties.getTokenParameter());
        if (StrUtil.isNotEmpty(token)&& jwtTokenUtils.validateToken(token)) {
            try {
                // 1.1 基于 token 构建登录用户
                LoginUser loginUser = buildLoginUserByToken(token);
                String username = jwtTokenUtils.getUsernameFromToken(token);
                // 1.2 模拟 Login 功能，方便日常开发调试
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authentication);
                // 2. 设置当前用户
                if (loginUser != null) {
                    SecurityFrameworkUtils.setLoginUser(loginUser, request);
                }
            } catch (Throwable ex) {
                throw ex;
            }
        }

        // 继续过滤链
        filterChain.doFilter(request, response);
    }

    private LoginUser buildLoginUserByToken(String token) {
        try {
            OAuth2AccessTokenCheckRespDTO accessToken = oauth2TokenApi.checkAccessToken(token);
            if (accessToken == null) {
                return null;
            }
            // 用户类型不匹配，无权限
            // 注意：只有 /admin-api/* 和 /app-api/* 有 userType，才需要比对用户类型
            // 类似 WebSocket 的 /ws/* 连接地址，是不需要比对用户类型的
            // 构建登录用户
            return new LoginUser().setId(accessToken.getUserId())
                    .setInfo(accessToken.getUserInfo()) // 额外的用户信息
                    .setTenantId(accessToken.getTenantId()).setScopes(accessToken.getScopes())
                    .setExpiresTime(accessToken.getExpiresTime());
        } catch (Exception serviceException) {
            // 校验 Token 不通过时，考虑到一些接口是无需登录的，所以直接返回 null 即可
            return null;
        }
    }
}
