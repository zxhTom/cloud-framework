package com.github.zxhtom.login.core.service;

import com.github.zxhtom.core.annotaion.login.NotLogin;
import com.github.zxhtom.login.core.model.Role;
import com.github.zxhtom.login.core.model.User;
import com.github.zxhtom.login.core.request.LoginRequest;
import com.github.zxhtom.login.core.response.LoginResponse;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

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
}
