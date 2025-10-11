package com.github.zxhtom.mini.controller;

import com.github.zxhtom.login.core.dto.CombineUser;
import com.github.zxhtom.login.core.service.MiniUserService;
import com.github.zxhtom.mini.dto.ApiResponse;
import com.github.zxhtom.mini.dto.Code2SessionResponse;
import com.github.zxhtom.mini.model.User;
import com.github.zxhtom.mini.service.WechatService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author zxhtom
 * 10/3/25
 */
@Api(value = "mini app login authorization interfaces")
@RestController
@RequestMapping(value = "/login")
@Slf4j
public class WechatLoginController {
    @Autowired
    private WechatService wechatService;
    @Autowired
    MiniUserService miniUserService;

    @PostMapping("/login")
    public ApiResponse login(@RequestBody WechatLoginRequest request) {

        // 1. 通过 code 获取 openid
        Code2SessionResponse sessionResponse = wechatService.code2Session(request.getCode());

        if (!sessionResponse.isSuccess()) {
            return ApiResponse.error("微信登录失败: " + sessionResponse.getErrmsg());
        }

        String openid = sessionResponse.getOpenid();
        CombineUser combineUser = miniUserService.selectMiniUserOrInitUserWithPrefix(request.getAppId(), openid, "wechat_");
        // 2. 业务逻辑：根据 openid 查找或创建用户
        User user = findOrCreateUser(openid, request.getUserInfo());

        // 3. 生成自定义登录态 (Token)
        String token = generateToken(user);

        // 4. (可选) 将 token 和 user 的关联关系存储到 Redis 或数据库
        // redisTemplate.opsForValue().set("token:" + token, openid, Duration.ofDays(7));

        // 5. 返回 token 和用户信息给前端
        LoginResponse loginResponse = new LoginResponse(token, user,combineUser.isRegisted());
        return ApiResponse.success(loginResponse);
    }

    private User findOrCreateUser(String openid, Object userInfo) {
        // 这里实现你的业务逻辑：
        // - 根据 openid 查询数据库，看用户是否存在
        // - 如果不存在，创建一个新用户（并保存昵称、头像等）
        // - 如果存在，更新最后登录时间等
        // 此处为示例，直接返回一个用户对象
        User user = new User();
        user.setOpenId(openid);
        user.setNickname("微信用户"); // 应从userInfo中获取
        return user;
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
    public static class LoginResponse {
        private String token;
        private User user;
        private boolean registed;
    }
}
