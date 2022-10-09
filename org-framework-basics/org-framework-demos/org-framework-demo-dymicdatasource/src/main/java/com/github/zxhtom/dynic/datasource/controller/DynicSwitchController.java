package com.github.zxhtom.dynic.datasource.controller;

import com.github.zxhtom.dynic.datasource.aop.DataSourceContextHolder;
import com.github.zxhtom.dynic.datasource.model.DynicTest;
import com.github.zxhtom.dynic.datasource.service.DynicTestService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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

    @Resource
    HttpServletRequest request;
    @RequestMapping(value = "/start",method = RequestMethod.GET)
    public List<DynicTest> start() {
        return dynicTestService.start();
    }

    @RequestMapping(value = "/startDb",method = RequestMethod.GET)
    public List<DynicTest> startDb() {
        String db = request.getHeader("db");
        DataSourceContextHolder.setDataSource(db);
        return dynicTestService.start();
    }

    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    public Integer insert(@RequestBody DynicTest dynicTest) {
        String db = request.getHeader("db");
        DataSourceContextHolder.setDataSource(db);
        return dynicTestService.insert(dynicTest);
    }
    @RequestMapping(value = "/db",method = RequestMethod.GET)
    public boolean db(@RequestParam String dbId) {
        return dynicTestService.switchDB(dbId);
    }
}
