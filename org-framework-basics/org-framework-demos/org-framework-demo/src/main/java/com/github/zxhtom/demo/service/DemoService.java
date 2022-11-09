package com.github.zxhtom.demo.service;

import java.util.List;
import java.util.Map;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.demo.service
 * @date 2021/8/23 16:22
 */
public interface DemoService {
    List<Map<String, Object>> selectTest(Integer code);
}
