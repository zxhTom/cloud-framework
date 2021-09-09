package com.github.zxhtom.exception.listener;

import com.github.zxhtom.core.properties.MaltCloudProperties;
import com.github.zxhtom.exception.event.BaseEvent;
import com.github.zxhtom.exception.log.ExceptionLogInDatasource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.exception.listener
 * @date 2021/8/31 17:01
 */
@Component
@Slf4j
public class ExceptionListener implements ApplicationListener<BaseEvent> {
    @Autowired
    ExceptionLogInDatasource exceptionLogInDatasource;
    @Autowired
    MaltCloudProperties.Listener listener;
    @Override
    public void onApplicationEvent(BaseEvent event) {
        if (!listener.isOpen()) {
            return;
        }
        Object msg = event.getSource();
        log.info("》》》》》开始本地化异常信息，可以通过异常instanceId定位日志");
        log.info(event.getInstanceId()+"@@@"+event.getSource());
        Integer result = exceptionLogInDatasource.saveExceptionIn(event);
        if (result > 0) {
            log.info("该异常已备案,您可以稍后进行查看");
        }
    }

}
