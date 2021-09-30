package com.github.zxhtom.core.datasouce;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.zxhtom.core.model.FillDataClassMapModel;
import com.github.zxhtom.web.auths.OnlineSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.core.datasouce
 * @date 2021/9/29 10:11
 */
@Component
@ConditionalOnMissingBean(FillDataHandler.class)
public class DefaultFillDataHandler implements FillDataHandler{
    @Autowired
    OnlineSecurity onlineSecurity;

    private List<FillDataClassMapModel> models = new ArrayList<>();
    <T, E extends T> Integer add(String fieldName, Class<T> fieldType, E fieldVal) {
        models.add(new FillDataClassMapModel(fieldName, fieldType, fieldVal));
        return 1;
    }
    @Override
    public List<FillDataClassMapModel> insertPerial() {
        models.clear();
        //自动管理数据入库时间
        add("createTime", Date.class, new Date());
        add("updateTime", Date.class, new Date());

        //管理数据操作用户信息
        add("owner", String.class, onlineSecurity.getOnlineUserName());
        add("modifer", String.class, onlineSecurity.getOnlineUserName());

        //管理接口信息
        add("interfaceEntry", String.class, onlineSecurity.getInterfaceName());

        //管理数据逻辑状态
        add("deleteFlag", Integer.class, 0);

        //管理数据版本字段
        add("version", Integer.class, 1);
        return models;
    }

    @Override
    public List<FillDataClassMapModel> updatePerial() {
        //自动管理数据更新库时间
        add("updateTime", Date.class, new Date());

        //管理数据操作用户信息
        add("modifer", String.class, onlineSecurity.getOnlineUserName());

        //管理接口信息
        add("interfaceEntry", String.class, onlineSecurity.getInterfaceName());
        return models;
    }
}
