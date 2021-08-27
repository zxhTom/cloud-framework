package com.github.zxhtom.datasource.mybatisPlus;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.github.zxhtom.web.auths.OnlineSecurity;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    OnlineSecurity onlineSecurity;
    @Override
    public void insertFill(MetaObject metaObject) {
        //自动管理数据入库时间
        this.strictInsertFill(metaObject, "createTime", Date.class, new Date());
        this.strictInsertFill(metaObject, "updateTime", Date.class, new Date());

        //管理数据操作用户信息
        this.strictInsertFill(metaObject, "owner", String.class, onlineSecurity.getOnlineUserName());
        this.strictInsertFill(metaObject, "modifer", String.class, onlineSecurity.getOnlineUserName());

        //管理接口信息
        this.strictInsertFill(metaObject, "interfaceEntry", String.class, onlineSecurity.getInterfaceName());

        //管理数据逻辑状态
        this.strictInsertFill(metaObject, "deleteFlag", Integer.class, 0);

        //管理数据版本字段
        this.strictInsertFill(metaObject, "version", Integer.class, 1);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        //自动管理数据更新库时间
        this.strictUpdateFill(metaObject, "updateTime", Date.class, new Date());

        //管理数据操作用户信息
        this.strictInsertFill(metaObject, "modifer", String.class, onlineSecurity.getOnlineUserName());

        //管理接口信息
        this.strictInsertFill(metaObject, "interfaceEntry", String.class, onlineSecurity.getInterfaceName());
    }
}
