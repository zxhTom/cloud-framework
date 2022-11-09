package com.github.zxhtom.demo.repository;

import java.util.List;
import java.util.Map;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.demo.repository
 * @date 2021/8/23 16:28
 */
public interface DemoRepository {
    public List<Map<String, Object>> selectTest();
}
