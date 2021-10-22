package com.github.zxhtom.core.annotaion;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.lang.annotation.*;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.core.annotaion
 * @date 2021/10/12 17:22
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface SuperDirectionHandler {
}
