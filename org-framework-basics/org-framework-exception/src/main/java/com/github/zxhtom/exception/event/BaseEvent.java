package com.github.zxhtom.exception.event;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.context.ApplicationEvent;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.exception.event
 * @date 2021/8/31 16:31
 */
@Data
@Accessors(chain = true)
public class BaseEvent extends ApplicationEvent {
    public BaseEvent(Object source) {
        super(source);
    }
    private String instanceId;

}
