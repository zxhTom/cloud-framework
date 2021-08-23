package com.github.zxhtom.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.demo.controller
 * @date 2021/8/23 11:04
 */
@RestController
@RequestMapping(value = "/demo")
public class DemoController {

    @RequestMapping(value = "/start",method = RequestMethod.GET)
    public String start() {
        return "hello";
    }
}
