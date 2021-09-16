package com.github.zxhtom.knife4j.demo.controller;

import com.github.zxhtom.knife4j.demo.model.Test;
import com.github.zxhtom.knife4j.demo.service.KnifeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.knife4j.demo.controller
 * @date 2021/9/14 15:52
 */
@Api(value = "KnifeController测试接口")
@RestController
@RequestMapping(value = "/knife")
@Slf4j
public class KnifeController {
    @Autowired
    KnifeService knifeService;

    @ApiOperation(value = "测试index接口", nickname = "测试IndexController的index接口")
    @RequestMapping(value = "/select",method = RequestMethod.GET)
    public List<Test> selectList(@ApiParam @RequestParam String userName , HttpServletRequest request) {
        log.info("大家好，我是{}",userName);
        return knifeService.selectList();
    }
}
