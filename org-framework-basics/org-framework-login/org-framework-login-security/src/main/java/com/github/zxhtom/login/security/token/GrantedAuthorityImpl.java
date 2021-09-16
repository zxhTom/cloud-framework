package com.github.zxhtom.login.security.token;

import org.springframework.security.core.GrantedAuthority;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.login.security.token
 * @date 2021/9/15 15:19
 */
public class GrantedAuthorityImpl implements GrantedAuthority {

    private static final long serialVersionUID = 1L;

    private String authority;

    public GrantedAuthorityImpl(String authority) {
        this.authority = authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return this.authority;
    }
}
