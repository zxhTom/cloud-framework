package com.github.zxhtom.login.security.controller;

import com.github.zxhtom.core.annotaion.login.NotLogin;
import com.github.zxhtom.login.core.model.Role;
import com.github.zxhtom.login.core.model.User;
import com.github.zxhtom.login.core.repository.RoleRepository;
import com.github.zxhtom.login.core.repository.UserRepository;
import com.github.zxhtom.login.core.request.LoginRequest;
import com.github.zxhtom.login.core.response.LoginResponse;
import com.github.zxhtom.login.security.utils.JwtTokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    RoleRepository roleRepository;
    @Autowired
    private AuthenticationConfiguration authenticationConfiguration ;

    @Autowired
    JwtTokenUtils jwtTokenUtils;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    @NotLogin
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationConfiguration.getAuthenticationManager().authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUserName(),
                            loginRequest.getPassword()
                    )
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

            return ResponseEntity.ok(response);

        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("code", 401, "message", "用户名或密码错误"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("code", 500, "message", "登录失败: " + e.getMessage()));
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody LoginRequest signUpRequest) {
        if (userRepository.existsByUserName(signUpRequest.getUserName())) {
            return ResponseEntity.badRequest()
                    .body(Map.of("code", 400, "message", "用户名已存在"));
        }

        // 创建新用户
        User user = new User();
        user.setUserName(signUpRequest.getUserName());
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
//        user.setRoles(Arrays.asList("USER"));

        userRepository.insert(user);

        return ResponseEntity.ok(Map.of("code", 200, "message", "用户注册成功"));
    }

    @GetMapping("/me")
    public ResponseEntity<?> getCurrentUser() {
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
        return ResponseEntity.ok(userInfo);
    }
}
