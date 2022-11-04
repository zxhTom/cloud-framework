package com.github.zxhtom.demo.exception.controller;

import com.github.zxhtom.demo.exception.model.Tom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO
 *
 * @author zxhtom
 * 2022/10/21
 */
@RestController
@RequestMapping(value = "/tom")
public class TomController {
    @Autowired
    Tom tom;

    @RequestMapping(value = "/test" , method = RequestMethod.GET)
    public void test() {
        System.out.println("tom = " + tom);
    }
}
