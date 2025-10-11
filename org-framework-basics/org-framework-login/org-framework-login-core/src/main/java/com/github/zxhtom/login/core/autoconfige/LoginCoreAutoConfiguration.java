package com.github.zxhtom.login.core.autoconfige;

import com.github.zxhtom.login.core.controller.LoginController;
import com.github.zxhtom.login.core.controller.captcha.CaptchaController;
import com.github.zxhtom.login.core.repository.*;
import com.github.zxhtom.login.core.repository.impl.*;
import com.github.zxhtom.login.core.service.*;
import com.github.zxhtom.login.core.service.impl.*;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.login.core.autoconfige
 * @date 2021/10/9 9:52
 */
@Configuration
public class LoginCoreAutoConfiguration {
    @Bean
    @ConditionalOnMissingBean(LoginController.class)
    public LoginController loginController() {
        return new LoginController();
    }

    @Bean
    @ConditionalOnMissingBean(LoginService.class)
    public LoginService loginService() {
        return new DefaultLoginServiceImpl();
    }

    @Bean
    @ConditionalOnMissingBean(UserService.class)
    public UserService userService() {
        return new UserServiceImpl();
    }

    @Bean
    @ConditionalOnMissingBean(ButtonService.class)
    public ButtonService buttonService() {
        return new ButtonServiceImpl();
    }

    @Bean
    @ConditionalOnMissingBean(PermissionUrlService.class)
    public PermissionUrlService permissionUrlService() {
        return new PermissionUrlServiceImpl();
    }

    @Bean
    @ConditionalOnMissingBean(MenuServiceImpl.class)
    public MenuService menuServiceImpl() {
        return new MenuServiceImpl();
    }

    @Bean
    @ConditionalOnMissingBean(ModuleServiceImpl.class)
    public ModuleService moduleServiceImpl() {
        return new ModuleServiceImpl();
    }

    @Bean
    @ConditionalOnMissingBean(MiniUserService.class)
    public MiniUserService miniUserServiceImpl() {
        return new MiniUserServiceImpl();
    }
    /********************************repository********************************/
    @Bean
    @ConditionalOnMissingBean(ButtonRepository.class)
    public ButtonRepository defaultButtonRepository() {
        return new ButtonRepositoryImpl();
    }
    @Bean
    @ConditionalOnMissingBean(MenuRepository.class)
    public MenuRepository defaultMenuRepository() {
        return new MenuRepositoryImpl();
    }
    @Bean
    @ConditionalOnMissingBean(ModuleRepository.class)
    public ModuleRepository defaultModuleRepository() {
        return new ModuleRepositoryImpl();
    }
    @Bean
    @ConditionalOnMissingBean(PermissionUrlRepository.class)
    public PermissionUrlRepository defaultPermissionUrlRepository() {
        return new PermissionUrlRepositoryImpl();
    }
    @Bean
    @ConditionalOnMissingBean(UserRepository.class)
    public UserRepository defaultUserRepository() {
        return new UserRepositoryImpl();
    }
    @Bean
    @ConditionalOnMissingBean(RoleRepository.class)
    public RoleRepository defaultRoleRepository() {
        return new RoleRepositoryImpl();
    }

    @Bean
    @ConditionalOnMissingBean(MiniUserRepository.class)
    public MiniUserRepository defaultMiniUserRepository() {
        return new MiniUserRepositoryImpl();
    }

    @Bean
    @ConditionalOnMissingBean(CaptchaController.class)
    public CaptchaController defaultCaptchaController() {
        return new CaptchaController();
    }

}

