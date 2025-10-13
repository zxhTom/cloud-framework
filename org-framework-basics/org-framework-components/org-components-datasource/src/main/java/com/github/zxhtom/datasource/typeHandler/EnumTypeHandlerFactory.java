package com.github.zxhtom.datasource.typeHandler;

import org.apache.ibatis.type.TypeHandler;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 枚举 TypeHandler 工厂
 * 解决 MyBatis 实例化问题
 */
public class EnumTypeHandlerFactory {

    private static final Map<Class<?>, TypeHandler<?>> handlerCache = new ConcurrentHashMap<>();

    @SuppressWarnings("unchecked")
    public static <E extends Enum<E>> TypeHandler<E> getTypeHandler(Class<E> enumType) {
        return (TypeHandler<E>) handlerCache.computeIfAbsent(enumType,
                type -> new GenericEnumTypeHandler<>((Class<E>) type));
    }

    /**
     * 清空缓存（主要用于测试和开发环境）
     */
    public static void clearCache() {
        handlerCache.clear();
    }
}
