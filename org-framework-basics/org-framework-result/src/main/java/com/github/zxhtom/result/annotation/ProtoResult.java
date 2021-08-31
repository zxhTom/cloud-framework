package com.github.zxhtom.result.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.result.annotation
 * @date 2021/8/31 18:42
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD,ElementType.TYPE})
public @interface ProtoResult {

    String value() default "true";
}
