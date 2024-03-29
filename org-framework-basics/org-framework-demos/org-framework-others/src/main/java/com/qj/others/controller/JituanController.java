package com.qj.others.controller;

import com.qj.others.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
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
@RequestMapping(value = "/dataExchange")
public class JituanController {

    @Autowired
    DemoService demoService;
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public Map<String,Object> login() {
        return demoService.selectTest(1);
    }

    @RequestMapping(value = "/riskReport",method = RequestMethod.POST)
    public String start(@RequestBody Map<String,Object> params) {
        System.out.println(params);
        return "hello";
    }
    @RequestMapping(value = "/reportHDanger",method = RequestMethod.POST)
    public String reportHDanger(@RequestBody Map<String,Object> params) {
        System.out.println(params);
        return "hello";
    }
}
