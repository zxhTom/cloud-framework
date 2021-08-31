package com.github.zxhtom.exception.config;

import com.github.zxhtom.core.IdGenerator;
import com.github.zxhtom.exception.constant.ExceptionConstant;
import com.github.zxhtom.exception.event.BaseEvent;
import com.github.zxhtom.log.model.properties.LogFormat;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.exception.config
 * @date 2021/8/31 16:19
 */
@Component
@Slf4j
public class GlobalExceptionResolver implements HandlerExceptionResolver, Ordered, ApplicationContextAware {
    private ApplicationContext applicationContext;

    @Autowired
    LogFormat logFormat;
    @Autowired
    IdGenerator idGenerator;
    @SneakyThrows
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception exception) { log.info(exception.getCause().getMessage());
        log.error(String.format(logFormat.getFormat(), exception.getCause().getMessage()));
        String id = idGenerator.generateAndGetId();
        httpServletRequest.setAttribute(ExceptionConstant.INSTANCE_ID, id);
        this.publishEvent(new BaseEvent(exception).setInstanceId(id));
        return null;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public int getOrder() {
        return -2147483648;
    }

    void publishEvent(BaseEvent event){
        this.applicationContext.publishEvent(event);
    }
}
