package com.github.zxhtom.core.datasouce;

import com.alibaba.fastjson.JSONObject;
import com.github.zxhtom.core.model.FillDataClassMapModel;

import java.util.List;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.core.datasouce
 * @date 2021/9/29 10:10
 */
public interface FillDataHandler {

    public List<FillDataClassMapModel> insertPerial();

    public List<FillDataClassMapModel> updatePerial();
}
