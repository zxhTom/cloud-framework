package com.github.zxhtom.login.core.service.impl;

import com.github.zxhtom.login.core.model.User;
import com.github.zxhtom.login.core.request.LoginRequest;
import com.github.zxhtom.login.core.response.LoginResponse;
import com.github.zxhtom.login.core.service.LoginService;

import java.util.Map;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.login.core.service.impl
 * @date 2021/10/9 9:35
 */
public class DefaultLoginServiceImpl implements LoginService {
    @Override
    public Integer refreshRoles(String userName) {
        return 0;
    }

    @Override
    public LoginResponse authenticateUser(LoginRequest loginRequest) {
        return null;
    }

    @Override
    public LoginResponse authenticateUserNameOnly(LoginRequest loginRequest) {
        return null;
    }

    @Override
    public Integer registerUser(LoginRequest signUpRequest) {
        return null;
    }

    @Override
    public Map<String, Object> getCurrentUser() {
        return null;
    }

    @Override
    public User selectUser(Long userId) {
        return null;
    }
}
