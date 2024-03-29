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

    @RequestMapping(value = "/getShelve" , method = RequestMethod.GET)
    public void getShelve(HttpServletRequest request) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("inter shelve thread parameter name = " + request.getParameter("name")+","+request);
            }
        }).start();
        System.out.println("name = " + request.getParameter("name")+","+request);
    }

    @RequestMapping(value = "/get" , method = RequestMethod.GET)
    public void get(HttpServletRequest request) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("inter thread parameter name = " + request.getParameter("name")+","+request);
            }
        }).start();
        System.out.println("name = " + request.getParameter("name")+","+request);
    }

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
