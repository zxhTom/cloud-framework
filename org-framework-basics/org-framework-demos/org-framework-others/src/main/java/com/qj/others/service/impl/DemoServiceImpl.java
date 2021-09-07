package com.qj.others.service.impl;

import com.qj.others.config.EnvironmentConfig;
import com.qj.others.repository.DemoRepository;
import com.qj.others.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
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
    public Map<String, Object> selectTest(Integer code) {
        if (code != 1) {
            throw new NullPointerException("hello");
        }
        Environment evn = EnvironmentConfig.getEvn();
        String spring = evn.getProperty("spring");
        Map<String, Object> resultMap =
                demoRepository.selectTest();
        System.out.println(resultMap);
        return resultMap;
    }
}
