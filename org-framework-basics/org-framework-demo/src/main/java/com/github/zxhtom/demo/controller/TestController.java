package com.github.zxhtom.demo.controller;

import com.github.zxhtom.demo.model.Test;
import com.github.zxhtom.demo.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.demo.controller
 * @date 2021/8/25 15:31
 */
@RestController
@RequestMapping(value = "/test")
public class TestController {

    @Autowired
    TestService testService;

    @RequestMapping(value = "/selectTest")
    public List<Test> selectTest() {
        return testService.selectTest();
    }
}
