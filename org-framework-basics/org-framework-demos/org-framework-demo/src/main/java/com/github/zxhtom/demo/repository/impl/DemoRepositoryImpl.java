package com.github.zxhtom.demo.repository.impl;

import com.github.zxhtom.demo.mapper.DemoMapper;
import com.github.zxhtom.demo.repository.DemoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.demo.repository.impl
 * @date 2021/8/23 16:28
 */
@Repository
public class DemoRepositoryImpl implements DemoRepository {
    @Autowired
    DemoMapper demoMapper;
    @Override
    public List<Map<String, Object>> selectTest() {
        return demoMapper.selectTest();
    }
}
