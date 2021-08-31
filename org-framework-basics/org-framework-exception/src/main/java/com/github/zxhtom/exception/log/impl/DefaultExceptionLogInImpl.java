package com.github.zxhtom.exception.log.impl;

import com.github.zxhtom.exception.event.BaseEvent;
import com.github.zxhtom.exception.log.ExceptionLogInDatasource;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.exception.log.impl
 * @date 2021/8/31 17:16
 */
@Slf4j
public class DefaultExceptionLogInImpl implements ExceptionLogInDatasource {
    @Override
    public Integer saveExceptionIn(BaseEvent event) {
        log.warn("您暂未配置日志系统... , 意味着您无法根据instanceId反查日志详情哦!!!");
        return 0;
    }

    @Override
    public BaseEvent selectExceptionWithInId(String id) {
        return null;
    }
}
