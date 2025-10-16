package com.github.zxhtom.login.core.controller;

import com.github.zxhtom.core.exception.BusinessException;
import com.github.zxhtom.login.core.request.LoginRequest;
import com.github.zxhtom.login.core.response.LoginResponse;
import com.github.zxhtom.login.core.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.login.core.controller
 * @date 2021/10/9 9:33
 */
@RestController
@RequestMapping(value = "/login")
public class LoginController {

    @Autowired
    LoginService loginService;
    @RequestMapping(value = "/refresh",method = RequestMethod.GET)
    public Integer refreshRoles(@RequestParam String userName) {
        return loginService.refreshRoles(userName);
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        LoginResponse loginResponse = null;
        try {
            loginResponse = loginService.authenticateUser(loginRequest);
        } catch (BusinessException businessException) {
            businessException.printStackTrace();
            if (businessException.getCode() == HttpStatus.UNAUTHORIZED.value()) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(Map.of("code", 401, "message", "用户名或密码错误"));
            } else if (businessException.getCode() == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(Map.of("code", 500, "message", "登录失败: " + businessException.getMsg()));
            } else {
                return ResponseEntity.status(500).body(Map.of("code",500));
            }
        }

        return ResponseEntity.ok(loginResponse);
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody LoginRequest signUpRequest) {
        Integer code = loginService.registerUser(signUpRequest);
        if (code == 400) {
            return ResponseEntity.badRequest()
                    .body(Map.of("code", 400, "message", "用户名已存在"));
        }

        return ResponseEntity.ok(Map.of("code", 200, "message", "用户注册成功"));
    }

    @GetMapping("/me")
    public ResponseEntity<?> getCurrentUser() {
        return ResponseEntity.ok(loginService.getCurrentUser());
    }
}
