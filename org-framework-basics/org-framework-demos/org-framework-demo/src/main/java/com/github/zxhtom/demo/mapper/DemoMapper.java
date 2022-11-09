package com.github.zxhtom.demo.mapper;

import com.github.zxhtom.demo.model.Test;

import java.util.List;
import java.util.Map;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.demo.mapper
 * @date 2021/8/23 16:29
 */
public interface DemoMapper {

    List<Map<String, Object>> selectTest();

    Integer insertTestSelfSql(Test test);
}
