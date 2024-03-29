package com.github.zxhtom.demo.repository;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.zxhtom.demo.model.Test;

import java.util.List;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.demo.repository
 * @date 2021/8/25 15:32
 */
public interface TestRepository {
    public List<Test> selectTest();

    Integer insertTest(Test test);

    Integer updateTest(Test test);

    Integer deleteTest(Integer testId);

    Page<Test> selectTestPage(Page page);

    Integer insertTestBatch(List<Test> tests);

    Integer insertTestSelfSql(Test test);
}
