package com.github.zxhtom.core.utils;

import com.github.zxhtom.core.enums.BaseEnum;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.core.utils
 * @date 2021/9/8 16:19
 */
public class EnumUtils {

    public static <E extends BaseEnum<E>> BaseEnum valueOf(String enumCode, Class<E> clazz) {
        if (StringUtils.isBlank(enumCode)) {
            return null;
        }
        Map<Integer, E> enumMap = new HashMap<>();
        E[] enums = clazz.getEnumConstants();
        for (E e : enums) {
            if (e.getCode().toString().equals(enumCode)||e.toString().equals(enumCode)) {
                return e;
            }
        }
        throw new IllegalArgumentException("No element matches " + enumCode);
    }
}
