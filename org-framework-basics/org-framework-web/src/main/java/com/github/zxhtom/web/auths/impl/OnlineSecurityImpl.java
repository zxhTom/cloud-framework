package com.github.zxhtom.web.auths.impl;

import com.github.zxhtom.web.auths.OnlineSecurity;
import com.github.zxhtom.web.auths.ScopeStoreService;
import com.github.zxhtom.web.constant.WebConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.stereotype.Component;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.web.auths
 * @date 2021/8/27 10:07
 */
@Component
@ConditionalOnMissingBean(OnlineSecurity.class)
public class OnlineSecurityImpl implements  OnlineSecurity{

    @Autowired
    ScopeStoreService scopeStoreService;
    @Override
    public Object getOnlinePrincipal() {
        return new Object();
    }

    @Override
    public String getOnlineUserName() {
        return WebConstant.DEFAULT_USER_NAME;
    }

    @Override
    public String getInterfaceName() {
        return scopeStoreService.getEntryPath();
    }
}
