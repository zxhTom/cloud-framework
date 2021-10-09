package com.github.zxhtom.login.security.handler;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.login.security.handler
 * @date 2021/10/9 13:44
 */
public class HandlerFactory {

    private Map<Class, RoleUrlHandler> clazzMap = new HashMap<>();
    private Map<Class, RoleUrlHandler> clazzLastMap = new HashMap<>();

    private static HandlerFactory util = new HandlerFactory();

    public static HandlerFactory getInstance(){
        return util;
    }


    public RoleUrlHandler registerHandler(AbstractRoleUrlHandler handler) {
        Class clazz = handler.getInstanceClass();
        if (clazzLastMap.containsKey(clazz)) {
            RoleUrlHandler lastRoleUrlHandler = clazzLastMap.get(clazz);
            handler.setPreFlow(lastRoleUrlHandler);
            lastRoleUrlHandler.setNextFlow(handler);
            lastRoleUrlHandler = handler;
        } else {
            clazzMap.put(clazz, handler);
        }
        clazzLastMap.put(clazz, handler);
        return handler;
    }

    public RoleUrlHandler registerHandlerToHead(AbstractRoleUrlHandler handler) {
        Class clazz = handler.getInstanceClass();
        if (clazzMap.containsKey(clazz)) {
            RoleUrlHandler preRoleUrlHandler = clazzMap.get(clazz);
            handler.setNextFlow(preRoleUrlHandler);
            preRoleUrlHandler.setPreFlow(handler);
            preRoleUrlHandler = handler;
        }else {
            clazzMap.put(clazz, handler);
        }
        return handler;
    }

    public RoleUrlHandler chain(Class clazz) {
        if (!clazzMap.containsKey(clazz)) {
            clazzMap.put(clazz, clazzLastMap.get(clazz));
        }
        return clazzMap.get(clazz);
    }
}
