package com.github.zxhtom.dynic.datasource.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.zxhtom.dynic.datasource.mapper.DynicTestMapper;
import com.github.zxhtom.dynic.datasource.model.DynicTest;
import com.github.zxhtom.dynic.datasource.service.DynicTestService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zxhtom
 * 2022/9/27
 */
@Service
public class DynicTestServiceImpl implements DynicTestService {
    @Resource
    DynicTestMapper dynicTestMapper;
    @Override
    public List<DynicTest> start() {
        return dynicTestMapper.selectList(new QueryWrapper<>());
    }
}
