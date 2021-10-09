package com.github.zxhtom.login.security.handler;

import org.springframework.security.access.ConfigAttribute;

import java.util.Collection;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.login.security.handler
 * @date 2021/10/9 13:13
 */
public interface RoleUrlHandler<T,R> {
    boolean hasNext();

    /**
     * 获取上一个节点
     * @return
     */
    public RoleUrlHandler getPreFlow();
    public RoleUrlHandler getNextFlow();
    /**
     * 添加下一个工作到工作流上
     * @param flow
     * @return
     */
    public RoleUrlHandler setNextFlow(RoleUrlHandler flow);

    /**
     * 设置上一个工作流
     * @param flow
     * @return
     */
    public RoleUrlHandler setPreFlow(RoleUrlHandler flow);

    public R execute(T obj);
}
