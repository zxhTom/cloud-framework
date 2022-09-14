package com.github.zxhtom.demo.transactional.service.impl;

import com.baomidou.mybatisplus.annotation.TableName;
import com.github.zxhtom.demo.transactional.mapper.TransMapper;
import com.github.zxhtom.demo.transactional.model.Transmodel;
import com.github.zxhtom.demo.transactional.service.TansService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

/**
 * TODO
 *
 * @author zxhtom
 * 2022/1/23
 */
@Service
@Slf4j
public class TansServiceImpl implements TansService {
    @Autowired
    TransMapper transMapper;
    @Override
    @Transactional
    public Map<String, Object> startTransaction(Transmodel transmodel) {
        int insert = transMapper.insert(transmodel);
        log.info("操作日志：" + insert);
        if ("zxhtom".equals(transmodel.getName())) {
            throw new RuntimeException("error");
        }
        return new HashMap<>();
    }
}
