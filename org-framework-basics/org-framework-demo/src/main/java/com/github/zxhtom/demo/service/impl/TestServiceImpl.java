package com.github.zxhtom.demo.service.impl;

import com.github.zxhtom.demo.model.Test;
import com.github.zxhtom.demo.repository.TestRepository;
import com.github.zxhtom.demo.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.demo.service.impl
 * @date 2021/8/25 15:48
 */
@Service
public class TestServiceImpl implements TestService {
    @Autowired
    TestRepository testRepository;
    @Override
    public List<Test> selectTest() {
        return testRepository.selectTest();
    }
}
