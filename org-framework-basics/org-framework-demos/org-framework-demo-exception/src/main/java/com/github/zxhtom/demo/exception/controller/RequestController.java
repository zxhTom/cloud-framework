package com.github.zxhtom.demo.exception.controller;

import org.apache.catalina.connector.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 用于模拟测试request异步开发
 * @author zxhtom
 * 2022/9/14
 */
@RestController
@RequestMapping(value = "/request")
public class RequestController {

    @RequestMapping(value = "/first" , method = RequestMethod.GET)
    public void first(HttpServletRequest request) {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("name = " + request.getParameter("name"));
    }
}
