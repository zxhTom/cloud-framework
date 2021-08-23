package com.github.zxhtom.demo.service.impl;

import com.github.zxhtom.demo.repository.DemoRepository;
import com.github.zxhtom.demo.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.demo.service.impl
 * @date 2021/8/23 16:22
 */
@Service
public class DemoServiceImpl implements DemoService {
    @Autowired
    DemoRepository demoRepository;

    @Override
    public Map<String, Object> selectTest() {
        Map<String, Object> resultMap =
                demoRepository.selectTest();
        System.out.println(resultMap);
        return resultMap;
    }
}
