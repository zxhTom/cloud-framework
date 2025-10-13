package com.github.zxhtom.login.security.service.impl;

import com.github.zxhtom.core.annotaion.login.NotLogin;
import com.github.zxhtom.core.exception.BusinessException;
import com.github.zxhtom.login.core.model.Role;
import com.github.zxhtom.login.core.model.User;
import com.github.zxhtom.login.core.repository.RoleRepository;
import com.github.zxhtom.login.core.repository.UserRepository;
import com.github.zxhtom.login.core.request.LoginRequest;
import com.github.zxhtom.login.core.response.LoginResponse;
import com.github.zxhtom.login.core.service.LoginService;
import com.github.zxhtom.login.security.token.UsernameOnlyAuthenticationToken;
import com.github.zxhtom.login.security.utils.JwtTokenUtils;
import com.github.zxhtom.login.security.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.login.security.service.impl
 * @date 2021/10/9 10:00
 */
public class LoginSecurityService implements LoginService {

    @Autowired
    RoleRepository roleRepository;
    @Autowired
    private AuthenticationConfiguration authenticationConfiguration ;

    @Autowired
    JwtTokenUtils jwtTokenUtils;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    @Lazy
    private PasswordEncoder passwordEncoder;
    @Override
    public Integer refreshRoles(String userName) {
        SecurityUtils.reloadUserAuthority(userName);
        return 1;
    }
    @Override
    public LoginResponse authenticateUser(LoginRequest loginRequest) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                loginRequest.getUserName(),
                loginRequest.getPassword()
        );
        return authenticateUser(usernamePasswordAuthenticationToken);
    }

    @Override
    public LoginResponse authenticateUserNameOnly(LoginRequest loginRequest) {
        UsernameOnlyAuthenticationToken usernameOnlyAuthenticationToken = new UsernameOnlyAuthenticationToken(loginRequest.getUserName());
        return authenticateUser(usernameOnlyAuthenticationToken);
    }

    @Override
    public Integer registerUser(@Valid @RequestBody LoginRequest signUpRequest) {
        if (userRepository.existsByUserName(signUpRequest.getUserName())) {
                    return 400;
        }

        // 创建新用户
        User user = new User();
        user.setUserName(signUpRequest.getUserName());
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
//        user.setRoles(Arrays.asList("USER"));

        userRepository.insert(user);

        return 200;
    }

    @Override
    public Map<String, Object> getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        User user = Optional.of(userRepository.findByUsername(username))
                .orElseThrow(() -> new RuntimeException("用户不存在"));

        Map<String, Object> userInfo = new HashMap<>();
        userInfo.put("id", user.getId());
        userInfo.put("username", user.getUserName());
        userInfo.put("email", user.getEmail());
        List<Role> roleList = roleRepository.selectRolesByUserId(user.getId());
        List<String> roles = roleList.stream().map(Role::getName).collect(Collectors.toList());
        userInfo.put("roles", roles);
        return userInfo;
    }

    private LoginResponse authenticateUser(AbstractAuthenticationToken authenticationToken) {
        try {
            Authentication authentication = authenticationConfiguration.getAuthenticationManager().authenticate(
                    authenticationToken
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtTokenUtils.generateToken(authentication);

            // 获取用户信息
            User user = Optional.of(userRepository.findByUsername(authentication.getName()))
                    .orElseThrow(() -> new RuntimeException("用户不存在"));

            Date expiration = jwtTokenUtils.getExpirationDateFromToken(jwt);
            List<Role> roleList = roleRepository.selectRolesByUserId(user.getId());
            List<String> roles = roleList.stream().map(Role::getName).collect(Collectors.toList());
            LoginResponse response = new LoginResponse(
                    jwt,
                    user.getUserName(),
                    roles,
                    expiration
            );

            return response;

        } catch (BadCredentialsException e) {
            throw new BusinessException(HttpStatus.UNAUTHORIZED.value(), "用户名或密码错误");
        } catch (Exception e) {
            throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR.value(), String.format("登录失败:%s", e.getMessage()));
        }
    }

}
