package com.github.zxhtom.demo.login.controller;

import com.github.zxhtom.demo.login.model.User;
import com.github.zxhtom.login.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.demo.login.controller
 * @date 2021/9/29 10:33
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public Integer insertUser(@RequestBody com.github.zxhtom.login.core.model.User user) {
        try {
            userService.insertUser(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 1;
    }

}
