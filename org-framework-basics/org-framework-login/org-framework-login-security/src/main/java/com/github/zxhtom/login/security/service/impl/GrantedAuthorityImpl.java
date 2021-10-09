package com.github.zxhtom.login.security.service.impl;
import org.springframework.security.core.GrantedAuthority;

/**
* @author zxhtom
* @Date 10:04 2021/10/9
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