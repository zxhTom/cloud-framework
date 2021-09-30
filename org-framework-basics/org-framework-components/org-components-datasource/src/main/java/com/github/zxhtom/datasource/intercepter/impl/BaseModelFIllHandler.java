package com.github.zxhtom.datasource.intercepter.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.zxhtom.core.model.FillDataClassMapModel;
import com.github.zxhtom.datasource.intercepter.AbstractFillHandler;
import com.github.zxhtom.datasource.model.BaseModel;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.datasource.intercepter.impl
 * @date 2021/9/29 16:17
 */
public class BaseModelFIllHandler extends AbstractFillHandler {
    @Override
    public boolean doSupport(Object parameter) {
        return BaseModel.class.isAssignableFrom(parameter.getClass());
    }

    @Override
    public void doFill(List<FillDataClassMapModel> list) {
        JSONObject jsonObject = new JSONObject();
        if (CollectionUtils.isNotEmpty(list)) {
            for (FillDataClassMapModel model : list) {
                jsonObject.put(model.getName(), model.getE());
            }
        }
        try {
            BeanUtils.populate(parameter, JSONObject.parseObject(JSON.toJSONString(jsonObject), Map.class));
        } catch (IllegalAccessException | InvocationTargetException illegalAccessException) {
            illegalAccessException.printStackTrace();
        }
    }

    @Override
    public void fillInParameterUser(String key, Object onlinePrincipal) {
        Map<String, Object> map = new HashMap<>();
        map.put(key, onlinePrincipal);
        try {
            BeanUtils.populate(parameter, map);
        } catch (IllegalAccessException | InvocationTargetException illegalAccessException) {
            illegalAccessException.printStackTrace();
        }
    }
}
