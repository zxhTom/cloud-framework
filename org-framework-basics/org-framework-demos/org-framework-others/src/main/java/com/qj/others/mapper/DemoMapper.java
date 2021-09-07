package com.qj.others.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.demo.mapper
 * @date 2021/8/23 16:29
 */
@Mapper
public interface DemoMapper {

    Map<String, Object> selectTest();

}
