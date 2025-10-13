package com.github.zxhtom.core.enums;

import java.lang.annotation.*;

/**
 * 枚举值注解，标记枚举在数据库中存储的值
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface EnumValue {
    String value() default "";
}
