package com.github.zxhtom.demo.exception.service.impl;

import com.github.zxhtom.demo.exception.service.AService;
import com.github.zxhtom.demo.exception.service.BService;
import org.springframework.stereotype.Service;

/**
 * @author zxhtom
 * 2022/9/13
 */
@Service
public class ABServiceImpl implements AService, BService {
    @Override
    public String sayHelloA() {
        return "A";
    }

    @Override
    public String sayHelloB() {
        return "B";
    }
}
