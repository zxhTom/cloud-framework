package com.github.zxhtom.login.core.service;

import com.github.zxhtom.login.core.model.User;
import com.github.zxhtom.login.core.request.LoginRequest;
import com.github.zxhtom.login.core.response.LoginResponse;

import java.util.Map;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.login.core.service
 * @date 2021/10/9 9:34
 */
public interface LoginService {

    Integer refreshRoles(String userName);

    public LoginResponse authenticateUser(LoginRequest loginRequest) ;

    public LoginResponse authenticateUserNameOnly(LoginRequest loginRequest) ;

    public Integer registerUser(LoginRequest signUpRequest);

    public Map<String, Object> getCurrentUser() ;

    User selectUser(Long userId);
}
