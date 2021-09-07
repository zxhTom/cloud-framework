package com.qj.others.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qj.others.mapper.TestMapper;
import com.qj.others.model.Test;
import com.qj.others.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.demo.repository.impl
 * @date 2021/8/25 15:33
 */
@Repository
public class TestRepositoryImpl implements TestRepository {
    @Autowired
    TestMapper testMapper;

    @Override
    public List<Test> selectTest() {
        return testMapper.selectList(new QueryWrapper<>());
    }

    @Override
    public Integer insertTest(Test test) {
        return testMapper.insert(test);
    }

    @Override
    public Integer updateTest(Test test) {
        Test dataSourceTest = testMapper.selectById(test.getId());
        return testMapper.updateById(dataSourceTest);
    }

    @Override
    public Integer deleteTest(Integer testId) {
        return testMapper.deleteById(testId);
    }

    @Override
    public Page<Test> selectTestPage(Page page) {
        Page resultPage = testMapper.selectPage(page, null);
        return resultPage;
    }

    @Override
    public Integer insertTestBatch(List<Test> tests) {
        return testMapper.insertBatchInTurn(tests,1);
    }

}
