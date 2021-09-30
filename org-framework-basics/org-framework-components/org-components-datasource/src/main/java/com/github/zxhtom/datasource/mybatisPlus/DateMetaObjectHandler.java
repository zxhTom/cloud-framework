package com.github.zxhtom.datasource.mybatisPlus;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.github.zxhtom.core.datasouce.FillDataHandler;
import com.github.zxhtom.core.model.FillDataClassMapModel;
import com.github.zxhtom.datasource.model.BaseModel;
import com.github.zxhtom.web.auths.OnlineSecurity;
import org.apache.commons.collections.CollectionUtils;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.datasource.mybatisPlus
 * @date 2021/8/26 15:35
 */
@Component
public class DateMetaObjectHandler implements MetaObjectHandler {

    @Autowired
    FillDataHandler fillDataHandler;
    @Override
    public void insertFill(MetaObject metaObject) {
        List<FillDataClassMapModel> list = fillDataHandler.insertPerial();
        if (CollectionUtils.isNotEmpty(list)) {
            for (FillDataClassMapModel model : list) {
                this.strictInsertFill(metaObject, model.getName(), model.getCt(), model.getE());
            }
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        List<FillDataClassMapModel> list = fillDataHandler.updatePerial();
        if (CollectionUtils.isNotEmpty(list)) {
            for (FillDataClassMapModel model : list) {
                this.strictUpdateFill(metaObject, model.getName(), model.getCt(), model.getE());
            }
        }
    }
}
