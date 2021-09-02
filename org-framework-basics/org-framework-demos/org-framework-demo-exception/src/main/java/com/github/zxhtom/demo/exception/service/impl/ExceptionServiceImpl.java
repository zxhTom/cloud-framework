package com.github.zxhtom.demo.exception.service.impl;

import com.github.zxhtom.demo.exception.service.ExceptionService;
import org.springframework.stereotype.Service;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.demo.exception.service.impl
 * @date 2021/9/2 9:10
 */
@Service
public class ExceptionServiceImpl implements ExceptionService {
    @Override
    public void test() {
        throw new NullPointerException("null hello");
    }
}
