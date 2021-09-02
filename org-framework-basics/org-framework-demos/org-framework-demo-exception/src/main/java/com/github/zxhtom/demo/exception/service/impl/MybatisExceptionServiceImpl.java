package com.github.zxhtom.demo.exception.service.impl;

import com.github.zxhtom.core.model.SystemExceptionCode;
import com.github.zxhtom.core.service.SystemService;
import org.springframework.stereotype.Service;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.demo.service.impl
 * @date 2021/8/31 19:31
 */
@Service
public class MybatisExceptionServiceImpl implements SystemService {
    @Override
    public SystemExceptionCode selectCodeBaseOnExceptionClass(Class clazz) {
        if (clazz == NullPointerException.class) {
            return new SystemExceptionCode(clazz, 20000);
        }
        return null;
    }
}