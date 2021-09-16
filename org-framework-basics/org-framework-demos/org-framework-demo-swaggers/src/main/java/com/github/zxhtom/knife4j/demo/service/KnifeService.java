package com.github.zxhtom.knife4j.demo.service;

import com.github.zxhtom.knife4j.demo.model.Test;

import java.util.List;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.knife4j.demo.service
 * @date 2021/9/14 15:53
 */
public interface KnifeService {
    public List<Test> selectList();
}
