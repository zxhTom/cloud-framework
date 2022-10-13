package com.github.zxhtom.dynic.datasource.service.impl;

import com.github.zxhtom.dynic.datasource.aop.DatabaseList;
import com.github.zxhtom.dynic.datasource.service.DatabaseListService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DatabaseListServiceImpl implements DatabaseListService {
    @Override
    public List<DatabaseList> list() {
        return null;
    }
}
