package com.github.zxhtom.core.service;

import com.github.zxhtom.core.model.SystemExceptionCode;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.core.service
 * @date 2021/8/31 19:14
 */
public interface SystemService {

    public SystemExceptionCode selectCodeBaseOnExceptionClass(Class clazz);
}
