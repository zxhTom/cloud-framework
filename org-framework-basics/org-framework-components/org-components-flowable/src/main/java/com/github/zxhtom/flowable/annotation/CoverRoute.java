package com.github.zxhtom.flowable.annotation;

import java.lang.annotation.*;

/**
 * TODO
 *
 * @author zxhtom
 * 2023/9/12
 */

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface CoverRoute {
    String value() default "";
}
