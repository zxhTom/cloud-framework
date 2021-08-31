package com.github.zxhtom.core;

import java.util.UUID;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.core
 * @date 2021/8/31 16:41
 */
public class IdGeneratorImpl implements IdGenerator{
    @Override
    public String generateAndGetId() {
        return UUID.randomUUID().toString();
    }
}
