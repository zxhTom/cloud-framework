package com.github.zxhtom.datasource.mybatisPlus;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.datasource.mybatisPlus
 * @date 2021/8/26 15:35
 */
@Component
public class DateMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        //自动管理数据入库时间
        this.strictInsertFill(metaObject, "createTime", Date.class, new Date());
        this.strictInsertFill(metaObject, "updateTime", Date.class, new Date());
        //管理数据逻辑状态
        this.strictInsertFill(metaObject, "isFlag", Integer.class, 0);

        //管理数据版本字段
        this.strictInsertFill(metaObject, "version", Integer.class, 1);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        //自动管理数据更新库时间
        this.strictUpdateFill(metaObject, "updateTime", Date.class, new Date());
    }
}
