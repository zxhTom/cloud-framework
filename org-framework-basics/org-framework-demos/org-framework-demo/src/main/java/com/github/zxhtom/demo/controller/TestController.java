package com.github.zxhtom.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.zxhtom.demo.enums.SexEnum;
import com.github.zxhtom.demo.enums.StatusEnum;
import com.github.zxhtom.demo.model.Test;
import com.github.zxhtom.demo.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.demo.controller
 * @date 2021/8/25 15:31
 */
@RestController
@RequestMapping(value = "/test")
public class TestController {

    @Autowired
    TestService testService;

    @RequestMapping(value = "/selectTest",method = RequestMethod.GET)
    public List<Test> selectTest() {
        return testService.selectTest();
    }
    @RequestMapping(value = "/selectTestXml",method = RequestMethod.GET,produces = { "application/xml;charset=UTF-8" })
    public List<Test> selectTestXml() {
        return testService.selectTest();
    }
    @RequestMapping(value = "/selectTestPage",method = RequestMethod.GET)
    public Page<Test> selectTest(@RequestParam Integer pageNum , @RequestParam Integer pageSize) {
        return testService.selectTestPage(pageNum,pageSize);
    }

    @RequestMapping(value = "/insertTest" , method = RequestMethod.POST)
    public Integer insertTest(@RequestBody Test test) {
        return testService.insertTest(test);
    }
    @RequestMapping(value = "/insertTestXml" , method = RequestMethod.POST,consumes = { "application/xml;charset=UTF-8" })
    public Integer insertTestXml(@RequestBody Test test) {
        return testService.insertTest(test);
    }
    @RequestMapping(value = "/insertTestBatch" , method = RequestMethod.POST)
    public Integer insertTestBatch(@RequestBody List<Test> tests) {
        return testService.insertTestBatch(tests);
    }

    @RequestMapping(value = "/noTransactionInsertTestNoTransaction" , method = RequestMethod.POST)
    public Integer noTransactionInsertTestNoTransaction(@RequestBody Test test) {
        return testService.noTransactionInsertTestNoTransaction(test);
    }
    @RequestMapping(value = "/updateTest" , method = RequestMethod.PUT)
    public Integer updateTest(@RequestBody Test test) {
        return testService.updateTest(test);
    }

    @RequestMapping(value = "/deleteTest" , method = RequestMethod.DELETE)
    public Integer deleteTest(@RequestParam @NotNull Integer testId) {
        return testService.deleteTest(testId);
    }

    @RequestMapping(value = "/integerTest",method = RequestMethod.GET)
    public int integerTest(@RequestParam Date currentDate) {
        System.out.println(currentDate);
        return 1;
    }

    @RequestMapping(value = "/dateTest",method = RequestMethod.GET)
    public String dateTest(@RequestParam Date currentDate) {
        System.out.println(currentDate);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "zxh");
        jsonObject.put("age", 1);
        jsonObject.put("birthDate", currentDate);
        return jsonObject.toString();
    }
    @RequestMapping(value = "/enumTest",method = RequestMethod.GET)
    public SexEnum enumTest(@RequestParam SexEnum sexEnum) {

        return sexEnum;
    }
    @RequestMapping(value = "/dateTest2",method = RequestMethod.GET)
    public Date dateTest2() {
        return new Date();
    }


    @RequestMapping(value = "/returnTest",method = RequestMethod.GET)
    public Test returnTest(@RequestBody  Test test) {

        return test;
    }
    @RequestMapping(value = "/stringToEnumTest",method = RequestMethod.GET)
    public Test stringToEnumTest(@RequestParam  StatusEnum statusEnum) {
        Test test = new Test();
        test.setId(Integer.MAX_VALUE);
        test.setStatusEnum(statusEnum);
        return test;
    }
    @RequestMapping(value = "/selectBaseEnumTest",method = RequestMethod.GET)
    public Test selectBaseEnumTest() {
        Test test = new Test();
        test.setId(Integer.MAX_VALUE);
        test.setStatusEnum(StatusEnum.SUCCESS);
        return test;
    }
}
