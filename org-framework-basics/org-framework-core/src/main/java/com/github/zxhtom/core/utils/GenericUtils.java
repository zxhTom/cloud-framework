package com.github.zxhtom.core.utils;

import sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl;

import java.lang.reflect.Type;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.core.utils
 * @date 2021/9/9 11:14
 */
public class GenericUtils {

    private static GenericUtils util = new GenericUtils();

    public static GenericUtils getInstance(){
        return util;
    }

    public Class getInstanceClass(Class clazz) {
        Type[] genericInterfaces = clazz.getGenericInterfaces();
        for (Type genericInterface : genericInterfaces) {
            Type[] genericInterfaces1 = ((Class) genericInterface).getGenericInterfaces();
            for (Type type : genericInterfaces1) {
                String typeName = type.getTypeName();
                Type[] actualTypeArguments = ((ParameterizedTypeImpl) type).getActualTypeArguments();
                if (null==actualTypeArguments||actualTypeArguments.length==0) {
                    return null;
                }
                String fanxingName = actualTypeArguments[0].getTypeName();
                try {
                    return Class.forName(fanxingName);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
