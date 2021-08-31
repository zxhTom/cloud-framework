package com.github.zxhtom.demo.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.zxhtom.demo.model.Test;

import java.util.List;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.demo.service
 * @date 2021/8/25 15:47
 */
public interface TestService extends IService<Test> {

    public List<Test> selectTest();

    Integer insertTest(Test test);

    Integer updateTest(Test test);

    Integer deleteTest(Integer testId);

    Integer noTransactionInsertTestNoTransaction(Test test);

    Page<Test> selectTestPage(Integer pageNum, Integer pageSize);

    Integer insertTestBatch(List<Test> tests);
}
