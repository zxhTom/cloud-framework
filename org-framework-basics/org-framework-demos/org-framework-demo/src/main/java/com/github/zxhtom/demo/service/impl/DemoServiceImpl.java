package com.github.zxhtom.demo.service.impl;

import com.github.zxhtom.demo.config.EnvironmentConfig;
import com.github.zxhtom.demo.repository.DemoRepository;
import com.github.zxhtom.demo.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.List;
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
    public List<Map<String, Object>> selectTest(Integer code) {
        if (code != 1) {
            throw new NullPointerException("hello");
        }
        Environment evn = EnvironmentConfig.getEvn();
        String spring = evn.getProperty("spring");
        List<Map<String,Object>> resultList =
                demoRepository.selectTest();
        System.out.println(resultList);
        return resultList;
    }
}
