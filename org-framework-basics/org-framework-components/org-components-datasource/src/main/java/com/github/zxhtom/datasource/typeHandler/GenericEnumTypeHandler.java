package com.github.zxhtom.datasource.typeHandler;

import com.github.zxhtom.core.enums.BaseEnum;
import com.github.zxhtom.core.enums.EnumValue;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * 通用枚举类型处理器
 * 支持 MyBatis 无参构造和带参构造两种方式
 */
public class GenericEnumTypeHandler<E extends Enum<E>> extends BaseTypeHandler<E> {

    private Class<E> type;
    private final Map<Object, E> codeToEnumMap = new HashMap<>();
    private final Map<E, Object> enumToCodeMap = new HashMap<>();
    private ConverterStrategy strategy;

    /**
     * 无参构造函数 - 供 MyBatis 使用
     * 注意：使用无参构造后必须调用 setEnumType 方法设置类型
     */
    public GenericEnumTypeHandler() {
        // MyBatis 会使用这个构造函数，然后通过 setter 注入类型
    }

    /**
     * 带参构造函数 - 供手动注册使用
     */
    public GenericEnumTypeHandler(Class<E> type) {
        this.type = type;
        initialize();
    }

    /**
     * 设置枚举类型 - 用于无参构造后的初始化
     */
    public void setEnumType(Class<E> type) {
        if (this.type != null) {
            throw new IllegalStateException("Enum type has already been set");
        }
        this.type = type;
        initialize();
    }

    private void initialize() {
        if (this.type == null) {
            throw new IllegalStateException("Enum type must be set before initialization");
        }

        this.strategy = determineStrategy(type);
        initializeMappings();
    }

    private ConverterStrategy determineStrategy(Class<E> enumClass) {
        // 策略1: 检查是否有 @EnumValue 注解
        if (hasEnumValueAnnotation(enumClass)) {
            return ConverterStrategy.ANNOTATION;
        }

        // 策略2: 检查是否实现 CodeEnum 接口
        if (BaseEnum.class.isAssignableFrom(enumClass)) {
            return ConverterStrategy.CODE_ENUM;
        }

        // 策略3: 检查是否有自定义 fromCode 方法
        if (hasCustomFromCodeMethod(enumClass)) {
            return ConverterStrategy.CUSTOM_METHOD;
        }

        // 策略4: 默认使用枚举名称
        return ConverterStrategy.NAME;
    }

    private boolean hasEnumValueAnnotation(Class<E> enumClass) {
        try {
            for (Field field : enumClass.getDeclaredFields()) {
                if (field.isEnumConstant() && field.isAnnotationPresent(EnumValue.class)) {
                    return true;
                }
            }
        } catch (Exception e) {
            // ignore
        }
        return false;
    }

    private boolean hasCustomFromCodeMethod(Class<E> enumClass) {
        try {
            Method fromCodeMethod = enumClass.getMethod("fromCode", Object.class);
            return Modifier.isStatic(fromCodeMethod.getModifiers());
        } catch (NoSuchMethodException e) {
            return false;
        }
    }

    @SuppressWarnings("unchecked")
    private void initializeMappings() {
        E[] enumConstants = type.getEnumConstants();
        if (enumConstants == null) {
            throw new IllegalArgumentException(type.getSimpleName() + " does not represent an enum type");
        }

        for (E enumConstant : enumConstants) {
            Object code = determineCode(enumConstant);
            codeToEnumMap.put(code, enumConstant);
            enumToCodeMap.put(enumConstant, code);
        }
    }

    private Object determineCode(E enumConstant) {
        try {
            switch (strategy) {
                case ANNOTATION:
                    return getCodeFromAnnotation(enumConstant);
                case CODE_ENUM:
                    return getCodeFromInterface(enumConstant);
                case CUSTOM_METHOD:
                    return getCodeFromCustomMethod(enumConstant);
                case NAME:
                default:
                    return enumConstant.name();
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to determine code for enum: " + enumConstant, e);
        }
    }

    private Object getCodeFromAnnotation(E enumConstant) throws Exception {
        Field field = type.getDeclaredField(enumConstant.name());
        EnumValue enumValue = field.getAnnotation(EnumValue.class);
        if (enumValue != null && !enumValue.value().isEmpty()) {
            return parseCodeValue(enumValue.value());
        }
        return enumConstant.name();
    }

    @SuppressWarnings("unchecked")
    private Object getCodeFromInterface(E enumConstant) {
        return ((BaseEnum<Object>) enumConstant).getCode();
    }

    private Object getCodeFromCustomMethod(E enumConstant) throws Exception {
        Method getCodeMethod = type.getMethod("getCode");
        return getCodeMethod.invoke(enumConstant);
    }

    private Object parseCodeValue(String value) {
        try {
            // 尝试解析为整数
            return Integer.parseInt(value);
        } catch (NumberFormatException e1) {
            try {
                // 尝试解析为长整数
                return Long.parseLong(value);
            } catch (NumberFormatException e2) {
                // 返回字符串
                return value;
            }
        }
    }

    @SuppressWarnings("unchecked")
    private E convertFromCode(Object code) {
        if (code == null) {
            return null;
        }

        // 首先尝试直接从映射中获取
        E result = codeToEnumMap.get(code);

        if (result != null) {
            return result;
        }

        // 如果找不到，尝试使用自定义方法
        if (strategy == ConverterStrategy.CUSTOM_METHOD) {
            try {
                Method fromCodeMethod = type.getMethod("fromCode", Object.class);
                return (E) fromCodeMethod.invoke(null, code);
            } catch (Exception e) {
                // 忽略异常，继续尝试其他方式
            }
        }

        // 最后尝试字符串匹配
        if (code instanceof String) {
            try {
                return Enum.valueOf(type, (String) code);
            } catch (IllegalArgumentException e) {
                // 忽略异常
            }
        }

        throw new IllegalArgumentException("No enum constant " + type.getCanonicalName() + "." + code);
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, E parameter, JdbcType jdbcType) throws SQLException {
        if (type == null) {
            throw new SQLException("TypeHandler not properly initialized");
        }

        Object code = enumToCodeMap.get(parameter);
        if (code == null) {
            throw new SQLException("No code mapping found for enum: " + parameter);
        }

        if (code instanceof Integer) {
            ps.setInt(i, (Integer) code);
        } else if (code instanceof Long) {
            ps.setLong(i, (Long) code);
        } else if (code instanceof String) {
            ps.setString(i, (String) code);
        } else {
            ps.setObject(i, code);
        }
    }

    @Override
    public E getNullableResult(ResultSet rs, String columnName) throws SQLException {
        if (type == null) {
            throw new SQLException("TypeHandler not properly initialized");
        }

        Object code = rs.getObject(columnName);
        if (rs.wasNull()) {
            return null;
        }
        return convertFromCode(code);
    }

    @Override
    public E getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        if (type == null) {
            throw new SQLException("TypeHandler not properly initialized");
        }

        Object code = rs.getObject(columnIndex);
        if (rs.wasNull()) {
            return null;
        }
        return convertFromCode(code);
    }

    @Override
    public E getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        if (type == null) {
            throw new SQLException("TypeHandler not properly initialized");
        }

        Object code = cs.getObject(columnIndex);
        if (cs.wasNull()) {
            return null;
        }
        return convertFromCode(code);
    }

    /**
     * 转换策略枚举
     */
    private enum ConverterStrategy {
        ANNOTATION,      // 使用 @EnumValue 注解
        CODE_ENUM,       // 实现 CodeEnum 接口
        CUSTOM_METHOD,   // 自定义 fromCode 方法
        NAME            // 使用枚举名称
    }
}
