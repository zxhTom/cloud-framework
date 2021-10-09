package com.github.zxhtom.login.core.controller;

import com.github.zxhtom.login.core.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
