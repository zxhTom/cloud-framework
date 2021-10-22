package com.github.zxhtom.common.expressin.impl;

import com.github.zxhtom.common.expressin.RootChoiceExpression;
import com.github.zxhtom.web.auths.OnlineSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.common.expressin.impl
 * @date 2021/10/13 14:46
 */
@Service
public class DefaultChoiceExpression implements RootChoiceExpression {
    @Autowired
    OnlineSecurity onlineSecurity;

    @Override
    public boolean haslogined() {
        return onlineSecurity.getOnlinePrincipal()!=null;
    }

    @Override
    public boolean hasRole(String role) {
        return onlineSecurity.hasAnyRole(role);
    }

    @Override
    public boolean hasAnyRole(String... roles) {
        return onlineSecurity.hasAnyRole(roles);
    }
}
