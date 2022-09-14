package com.github.zxhtom.demo.exception.controller;

import com.github.zxhtom.demo.exception.service.AService;
import com.github.zxhtom.demo.exception.service.BService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zxhtom
 * 2022/9/13
 */
@RestController
@RequestMapping(value = "/componet")
public class MoreCompoentController {
    @Autowired
    AService aService;
    @Autowired
    BService bService;


    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public String test() {
        System.out.println(aService.sayHelloA());
        System.out.println(bService.sayHelloB());
        return "success";
    }
}
