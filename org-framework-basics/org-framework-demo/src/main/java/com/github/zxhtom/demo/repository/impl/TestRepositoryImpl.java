package com.github.zxhtom.demo.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.zxhtom.demo.mapper.TestMapper;
import com.github.zxhtom.demo.model.Test;
import com.github.zxhtom.demo.repository.TestRepository;
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
        Test test = new Test();
        test.setName("zxhtom");
        QueryWrapper<Test> queryWrapper = new QueryWrapper<>();
        queryWrapper.setEntity(test);
        return testMapper.selectList(queryWrapper);
    }
}
