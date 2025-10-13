package com.github.zxhtom.login.security.token;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * @author zxhtom
 * 10/12/25
 */

public class UsernameOnlyAuthenticationToken extends AbstractAuthenticationToken {

    private final Object principal;

    public UsernameOnlyAuthenticationToken(String username) {
        super(null);
        this.principal = username;
        setAuthenticated(false);
    }

    public UsernameOnlyAuthenticationToken(UserDetails principal, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
        super.setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return null; // 没有密码凭证
    }

    @Override
    public Object getPrincipal() {
        return this.principal;
    }
}
