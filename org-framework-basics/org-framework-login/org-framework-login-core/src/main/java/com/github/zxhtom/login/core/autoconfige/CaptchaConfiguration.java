package com.github.zxhtom.login.core.autoconfige;

import com.anji.captcha.config.AjCaptchaAutoConfiguration;
import com.anji.captcha.properties.AjCaptchaProperties;
import com.anji.captcha.service.CaptchaCacheService;
import com.anji.captcha.service.impl.CaptchaServiceFactory;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * 验证码的配置类
 *
 * @author 芋道源码
 */
@Configuration(proxyBeanMethods = false)
@ImportAutoConfiguration(AjCaptchaAutoConfiguration.class) // 目的：解决 aj-captcha 针对 SpringBoot 3.X 自动配置不生效的问题
public class CaptchaConfiguration {

    @Bean(name = "AjCaptchaCacheService")
    @Primary
    public CaptchaCacheService captchaCacheService(AjCaptchaProperties config) {
        CaptchaCacheService captchaCacheService = CaptchaServiceFactory.getCache(config.getCacheType().name());
        return captchaCacheService;
    }

}
