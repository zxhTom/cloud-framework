package com.github.zxhtom.core.annotaion.login;

import java.lang.annotation.*;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.core.annotaion.login
 * @date 2021/10/22 11:13
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface NotLogin {
}
