package com.github.zxhtom.dynic.datasource.controller;

import com.github.zxhtom.dynic.datasource.model.DynicTest;
import com.github.zxhtom.dynic.datasource.service.DynicTestService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * TODO
 *
 * @author zxhtom
 * 2022/9/27
 */
@RestController
@RequestMapping(value = "/dy")
public class DynicSwitchController {
    @Resource
    DynicTestService dynicTestService;

    @RequestMapping(value = "/start",method = RequestMethod.GET)
    public List<DynicTest> start() {
        return dynicTestService.start();
    }
}
