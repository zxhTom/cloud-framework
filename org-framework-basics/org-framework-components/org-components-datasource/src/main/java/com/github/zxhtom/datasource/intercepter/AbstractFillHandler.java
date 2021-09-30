package com.github.zxhtom.datasource.intercepter;

import com.github.zxhtom.core.datasouce.FillDataHandler;
import com.github.zxhtom.core.model.FillDataClassMapModel;
import com.github.zxhtom.web.auths.OnlineSecurity;
import com.github.zxhtom.web.context.ApplicationContextUtil;
import org.apache.ibatis.mapping.SqlCommandType;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.datasource.intercepter
 * @date 2021/9/29 16:03
 */
public abstract class AbstractFillHandler {

    public Object parameter;

    public boolean support(Object parameter){
        this.parameter = parameter;
        return doSupport(this.parameter);
    }

    public void fill(SqlCommandType type){
        FillDataHandler fillDataHandler = ApplicationContextUtil.getApplicationContext().getBean(FillDataHandler.class);
        OnlineSecurity onlineSecurity = ApplicationContextUtil.getApplicationContext().getBean(OnlineSecurity.class);
        fillInParameterUser("currentUser", onlineSecurity.getOnlinePrincipal());
        if (SqlCommandType.INSERT == type) {
            doFill(fillDataHandler.insertPerial());
        } else if (SqlCommandType.UPDATE == type) {
            doFill(fillDataHandler.updatePerial());
        }
    }

    public abstract void fillInParameterUser(String key, Object onlinePrincipal);

    public abstract boolean doSupport(Object parameter);
    public abstract void doFill(List<FillDataClassMapModel> list);

}
