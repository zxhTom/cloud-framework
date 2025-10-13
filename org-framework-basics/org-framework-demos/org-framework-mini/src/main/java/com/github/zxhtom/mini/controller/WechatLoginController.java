package com.github.zxhtom.mini.controller;

import com.github.zxhtom.login.core.dto.CombineUser;
import com.github.zxhtom.login.core.model.User;
import com.github.zxhtom.login.core.request.LoginRequest;
import com.github.zxhtom.login.core.response.LoginResponse;
import com.github.zxhtom.login.core.service.LoginService;
import com.github.zxhtom.login.core.service.MiniUserService;
import com.github.zxhtom.mini.dto.ApiResponse;
import com.github.zxhtom.mini.dto.Code2SessionResponse;
import com.github.zxhtom.mini.service.WechatService;
import com.github.zxhtom.result.annotation.ProtoResult;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.PermitAll;
import java.util.UUID;

/**
 * @author zxhtom
 * 10/3/25
 */
@Api(value = "mini app login authorization interfaces")
@RestController
@RequestMapping(value = "/wechat")
@Slf4j
@PermitAll
public class WechatLoginController {
    @Autowired
    private WechatService wechatService;
    @Autowired
    MiniUserService miniUserService;
    @Autowired
    LoginService loginService;
    @Autowired
    @Lazy
    PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    @ProtoResult
    public ApiResponse login(@RequestBody WechatLoginRequest request) {

        // 1. 通过 code 获取 openid
        Code2SessionResponse sessionResponse = wechatService.code2Session(request.getCode());

        if (!sessionResponse.isSuccess()) {
            return ApiResponse.error("微信登录失败: " + sessionResponse.getErrmsg());
        }

        String openid = sessionResponse.getOpenid();
        CombineUser combineUser = miniUserService.selectMiniUserOrInitUserWithPrefix(request.getAppId(), openid, "wechat_");
        // 2. 业务逻辑：根据 openid 查找或创建用户
        User user = combineUser.getMaltcloud();
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUserName(user.getUserName());
        user.setPassword("");
        LoginResponse loginResponse = loginService.authenticateUserNameOnly(loginRequest);

        // 3. 生成自定义登录态 (Token)
        String token = String.format("%s", loginResponse.getToken());

        // 4. (可选) 将 token 和 user 的关联关系存储到 Redis 或数据库
        // redisTemplate.opsForValue().set("token:" + token, openid, Duration.ofDays(7));

        // 5. 返回 token 和用户信息给前端
        WechatLoginResponse wechatLoginResponse = new WechatLoginResponse(token, user,combineUser.isRegisted());
        return ApiResponse.success(wechatLoginResponse);
    }


    private String generateToken(User user) {
        // 生成一个随机的、安全的 Token
        return UUID.randomUUID().toString().replace("-", "");
    }

    // 内部类：接收前端请求
    @Data
    public static class WechatLoginRequest {
        private String appId;
        private String code;
        private Object userInfo; // 可以是一个Map或自定义DTO
    }

    // 内部类：返回给前端的响应
    @Data
    @AllArgsConstructor
    public static class WechatLoginResponse {
        private String token;
        private User user;
        private boolean registed;
    }
}
