package com.github.zxhtom.demo.common.controller;

import com.github.zxhtom.common.service.CommonSystemService;
import com.github.zxhtom.core.annotaion.SuperDirection;
import com.github.zxhtom.core.annotaion.SuperDirectionHandler;
import com.github.zxhtom.demo.common.service.CommonTestService;
import com.github.zxhtom.web.context.ApplicationContextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.demo.common.controller
 * @date 2021/10/13 11:16
 */
@RestController
@RequestMapping(value = "/demo/common")
public class CommonController {
    @Autowired
    List<CommonSystemService> commonSystemServices;

    @Qualifier(value = "commonTestServiceImpl")
    @Autowired
    CommonTestService commonTestService;

    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public void test() {

        Map<String, Object> beansWithAnnotation = ApplicationContextUtil.getApplicationContext().getBeansWithAnnotation(SuperDirectionHandler.class);
        Map<String, Object> beansWithAnnotation2 = ApplicationContextUtil.getApplicationContext().getBeansWithAnnotation(SuperDirection.class);
        System.out.println(beansWithAnnotation);
        commonTestService.test();
    }
}
