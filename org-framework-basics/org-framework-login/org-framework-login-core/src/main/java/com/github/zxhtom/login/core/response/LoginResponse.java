package com.github.zxhtom.login.core.response;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class LoginResponse {
     private Long userId;
    private String token;
    private String type = "Bearer";
    private String username;
    private List<String> roles;
    private Date expiration;

    public LoginResponse(Long userId,String token, String username, List<String> roles, Date expiration) {
        this.userId = userId;
        this.token = token;
        this.username = username;
        this.roles = roles;
        this.expiration = expiration;
    }
}
