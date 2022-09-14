package com.github.zxhtom.demo.exception.controller;

import com.github.zxhtom.demo.exception.service.ExceptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.demo.exception.controller
 * @date 2021/9/2 9:07
 */
@RestController
@RequestMapping(value = "/exception")
public class ExceptionController {
    @Autowired
    ExceptionService exceptionService;

    @RequestMapping(value = "code" , method = RequestMethod.GET)
    public String code() {
        return UUID.randomUUID().toString();
    }

    @RequestMapping(value = "login" , method = RequestMethod.GET)
    public String login(@RequestParam String code) {
        System.out.println("code="+code);
        return UUID.randomUUID().toString();
    }
    @RequestMapping(value = "/test")
    public void test() {
        exceptionService.test();
    }
}
