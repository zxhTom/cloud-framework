package com.github.zxhtom.demo.exception.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * TODO
 *
 * @author zxhtom
 * 2022/11/16
 */
@RestController
@RequestMapping(value = "/baidu")
public class BaiduController {

    @RequestMapping(value = "code", method = RequestMethod.GET)
    public Map<String, Object> code(@RequestParam Map<String, Object> param) {
        System.out.println("param = " + param);
        return param;
    }
}
