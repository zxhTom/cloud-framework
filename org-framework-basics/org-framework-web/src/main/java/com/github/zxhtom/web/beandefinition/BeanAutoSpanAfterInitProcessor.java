package com.github.zxhtom.web.beandefinition;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.web.beandefinition
 * @date 2021/9/30 12:02
 */
public abstract class BeanAutoSpanAfterInitProcessor<T>{
    public Class getInstanceClass() {
        Type type = this.getClass().getGenericSuperclass();
        String typeName = type.getTypeName();
        Type[] actualTypeArguments = ((ParameterizedType) type).getActualTypeArguments();
        if (null==actualTypeArguments||actualTypeArguments.length==0) {
            return null;
        }
        String fanxingName = actualTypeArguments[0].getTypeName();
        try {
            return Class.forName(fanxingName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public abstract void initBeanDefinitionProperties(BeanDefinitionBuilder builder);
    public abstract String getScope();
}
