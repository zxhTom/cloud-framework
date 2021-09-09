package com.github.zxhtom.core.enums;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.github.zxhtom.core.converter.BaseEnumDeserializer;
import com.github.zxhtom.core.converter.BaseEnumSerializer;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.core.enums
 * @date 2021/9/8 15:07
 */
@JsonDeserialize(using = BaseEnumDeserializer.class)
@JsonSerialize(using = BaseEnumSerializer.class)
public interface BaseEnum<T> {
    T getValue();

    Integer getCode();


}
