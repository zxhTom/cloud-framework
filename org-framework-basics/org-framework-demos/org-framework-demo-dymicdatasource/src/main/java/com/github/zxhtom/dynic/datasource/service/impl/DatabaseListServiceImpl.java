package com.github.zxhtom.dynic.datasource.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.zxhtom.dynic.datasource.aop.DatabaseList;
import com.github.zxhtom.dynic.datasource.mapper.DatabaseListMapper;
import com.github.zxhtom.dynic.datasource.service.DatabaseListService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DatabaseListServiceImpl implements DatabaseListService {
    @Resource
    DatabaseListMapper databaseListMapper;
    @Override
    public List<DatabaseList> list() {
        return databaseListMapper.selectList(new QueryWrapper<>());
    }
}
