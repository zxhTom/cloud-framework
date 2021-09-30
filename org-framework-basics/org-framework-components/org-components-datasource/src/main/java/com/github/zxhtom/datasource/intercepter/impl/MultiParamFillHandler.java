package com.github.zxhtom.datasource.intercepter.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.zxhtom.core.model.FillDataClassMapModel;
import com.github.zxhtom.datasource.intercepter.AbstractFillHandler;
import org.apache.commons.collections.CollectionUtils;
import org.apache.ibatis.binding.MapperMethod;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.datasource.intercepter.impl
 * @date 2021/9/29 16:14
 */
public class MultiParamFillHandler extends AbstractFillHandler {
    @Override
    public void fillInParameterUser(String key, Object onlinePrincipal) {
        MapperMethod.ParamMap parameter = (MapperMethod.ParamMap) this.parameter;
        int size = parameter.size();
        parameter.put(key, onlinePrincipal);
        parameter.put("param"+(size/2+1), onlinePrincipal);
    }

    @Override
    public boolean doSupport(Object parameter) {
        return MapperMethod.ParamMap.class.isAssignableFrom(parameter.getClass());
    }

    @Override
    public void doFill(List<FillDataClassMapModel> list) {
        Map<String,Object> map = new HashMap<>();
        if (CollectionUtils.isNotEmpty(list)) {
            for (FillDataClassMapModel model : list) {
                fillInParameterUser(model.getName(), model.getE());
            }
        }
    }
}
