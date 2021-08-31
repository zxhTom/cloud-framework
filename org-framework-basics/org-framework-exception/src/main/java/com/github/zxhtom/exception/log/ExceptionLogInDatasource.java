package com.github.zxhtom.exception.log;

import com.github.zxhtom.exception.event.BaseEvent;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.exception.log
 * @date 2021/8/31 17:12
 */
public interface ExceptionLogInDatasource {
    /**
    * @author zxhtom
    * @Description 将异常信息入库，由子类实现，方便后期扩展es日志
    * @Date 17:13 2021/8/31
    */
    public Integer saveExceptionIn(BaseEvent event);

    /**
    * @author zxhtom
    * @Description 此Id在exception拦截出生成全局唯一id并跟随至客户端消息体
    * @Date 17:14 2021/8/31
    */
    public BaseEvent selectExceptionWithInId(String id);
    
}
