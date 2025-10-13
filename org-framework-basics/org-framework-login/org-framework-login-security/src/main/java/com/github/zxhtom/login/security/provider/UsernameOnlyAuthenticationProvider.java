package com.github.zxhtom.login.security.provider;

import com.github.zxhtom.login.security.token.JwtTokenProvider;
import com.github.zxhtom.login.security.token.UsernameOnlyAuthenticationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

/**
 * @author zxhtom
 * 10/12/25
 */

@Component
public class UsernameOnlyAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserDetailsService userDetailsService;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();

        // 1. 根据用户名加载用户信息
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        // 2. 验证用户状态（可选）
        if (!userDetails.isEnabled()) {
            throw new DisabledException("用户已被禁用");
        }
        if (!userDetails.isAccountNonLocked()) {
            throw new LockedException("用户已被锁定");
        }

        // 3. 创建已认证的Authentication对象
        return new UsernamePasswordAuthenticationToken(
                userDetails,
                null, // 密码设为null
                userDetails.getAuthorities()
        );
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernameOnlyAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
