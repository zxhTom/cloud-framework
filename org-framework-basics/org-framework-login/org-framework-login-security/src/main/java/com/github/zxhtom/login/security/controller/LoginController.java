package com.github.zxhtom.login.security.controller;

import com.github.zxhtom.login.security.model.HttpResult;
import com.github.zxhtom.login.security.model.LoginBean;
import com.github.zxhtom.login.security.token.JwtAuthenticatioToken;
import com.github.zxhtom.login.security.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.login.security.controller
 * @date 2021/9/15 15:25
 */
@RestController
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    /**
     * 登录接口
     */
    @PostMapping(value = "/login")
    public HttpResult login(@RequestBody LoginBean loginBean, HttpServletRequest request) throws IOException {
        String username = loginBean.getUsername();
        String password = loginBean.getPassword();

        // 系统登录认证
        JwtAuthenticatioToken token = SecurityUtils.login(request, username, password, authenticationManager);

        return HttpResult.ok(token);
    }

}

