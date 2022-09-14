package com.github.zxhtom.demo.transactional.service;

import com.github.zxhtom.demo.transactional.model.Transmodel;

import java.util.Map;

/**
 * TODO
 *
 * @author zxhtom
 * 2022/1/23
 */
public interface TansService {
    Map<String, Object> startTransaction(Transmodel transmodel);
}
