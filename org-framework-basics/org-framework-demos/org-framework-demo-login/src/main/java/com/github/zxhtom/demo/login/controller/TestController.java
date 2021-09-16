package com.github.zxhtom.demo.login.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.demo.login.controller
 * @date 2021/9/15 9:52
 */
@RestController
@RequestMapping(value = "/test")
public class TestController {


    @RequestMapping(value = "/hello")
    public String hello() {
        return "hello zxhtom";
    }

    @RequestMapping(value = "/hello2")
    public String hello2() {
        return "hello zxhtom2";
    }
}
