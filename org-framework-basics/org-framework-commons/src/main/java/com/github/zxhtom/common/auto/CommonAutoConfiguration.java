package com.github.zxhtom.common.auto;

import com.github.zxhtom.common.aspect.MoreChoiceAspect;
import com.github.zxhtom.common.controller.CommonUserController;
import com.github.zxhtom.common.controller.SystemController;
import com.github.zxhtom.common.expressin.RootChoiceExpression;
import com.github.zxhtom.common.expressin.impl.DefaultChoiceExpression;
import com.github.zxhtom.common.repository.CommonUserRepository;
import com.github.zxhtom.common.repository.SystemRepository;
import com.github.zxhtom.common.repository.impl.CommonUserRepositoryImpl;
import com.github.zxhtom.common.repository.impl.SystemRepositoryImpl;
import com.github.zxhtom.common.service.CommonSystemService;
import com.github.zxhtom.common.service.CommonUserService;
import com.github.zxhtom.common.service.impl.CommonSystemServiceImpl;
import com.github.zxhtom.common.service.impl.CommonUserServiceImpl;
import com.github.zxhtom.core.annotaion.SuperDirectionHandler;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.common.auto
 * @date 2021/10/12 16:25
 */
@Configuration
public class CommonAutoConfiguration {

    /********************************controller********************************/
    @Bean
    @ConditionalOnMissingBean(SystemController.class)
    public SystemController systemController() {
        return new SystemController();
    }

    @Bean
    @ConditionalOnMissingBean(CommonUserController.class)
    public CommonUserController commonUserController() {
        return new CommonUserController();
    }
    /********************************service********************************/
    @Bean
    @ConditionalOnMissingBean(CommonSystemService.class)
    public CommonSystemService defaultSystemService() {
        return new CommonSystemServiceImpl();
    }

    @Bean
    @ConditionalOnMissingBean(CommonUserService.class)
    public CommonUserService defaultCommonUserService() {
        return new CommonUserServiceImpl();
    }

    /********************************repository********************************/
    @Bean
    @ConditionalOnMissingBean(SystemRepository.class)
    public SystemRepository defaultSystemRepository() {
        return new SystemRepositoryImpl();
    }

    @Bean
    @ConditionalOnMissingBean(CommonUserRepository.class)
    public CommonUserRepository defaultCommonUserRepository() {
        return new CommonUserRepositoryImpl();
    }

    /********************************compoents********************************/

    @Bean
    @ConditionalOnMissingBean(RootChoiceExpression.class)
    public RootChoiceExpression defaultRootChoiceExpression() {
        return new DefaultChoiceExpression();
    }
    /********************************aspect********************************/
    @Bean
    @ConditionalOnMissingBean(MoreChoiceAspect.class)
    public MoreChoiceAspect moreChoiceAspect() {
        return new MoreChoiceAspect();
    }
}
