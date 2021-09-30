package com.github.zxhtom.web.beandefinition.child;

import com.github.zxhtom.web.auths.OnlineSecurity;
import com.github.zxhtom.web.beandefinition.BeanAutoSpanAfterInitProcessor;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.web.beandefinition.child
 * @date 2021/9/30 14:02
 */
@Component
public class OnlineSecurityBeanSpan extends BeanAutoSpanAfterInitProcessor<OnlineSecurity> {
    @Override
    public void initBeanDefinitionProperties(BeanDefinitionBuilder builder) {
        System.out.println("@@@@@@@@");
    }

    @Override
    public String getScope() {
        return WebApplicationContext.SCOPE_REQUEST;
    }
}
