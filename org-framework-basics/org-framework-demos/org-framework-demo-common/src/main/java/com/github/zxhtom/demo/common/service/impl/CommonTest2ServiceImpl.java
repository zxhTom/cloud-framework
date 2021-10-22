package com.github.zxhtom.demo.common.service.impl;

import com.github.zxhtom.core.annotaion.SuperDirectionHandler;
import com.github.zxhtom.demo.common.service.CommonTestService;
import org.springframework.stereotype.Service;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.demo.common.service.impl
 * @date 2021/10/13 14:30
 */
@Service
@SuperDirectionHandler
public class CommonTest2ServiceImpl implements CommonTestService {
    @Override
    public void test() {
        System.out.println("hello test 2");
    }
}
