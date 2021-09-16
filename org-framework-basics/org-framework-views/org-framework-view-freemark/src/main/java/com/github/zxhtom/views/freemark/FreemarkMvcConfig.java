package com.github.zxhtom.views.freemark;

import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.freemarker.FreeMarkerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerView;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.Properties;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.views.freemark
 * @date 2021/9/16 9:24
 */
@Configuration
@ConditionalOnProperty(prefix = "spring.freemarker",value = "enabled", matchIfMissing = true)
public class FreemarkMvcConfig implements WebMvcConfigurer {

    @Autowired
    FreeMarkerProperties freeMarkerProperties;


    @Bean
    public FreeMarkerConfigurer freeMarkerConfigurer(){
        FreeMarkerConfigurer configurer = new FreeMarkerConfigurer();
        String[] templateLoaderPath = freeMarkerProperties.getTemplateLoaderPath();
        String[] lastPaths = new String[templateLoaderPath.length + 2];
        lastPaths[0] = "classpath:/freemarker/";
        lastPaths[1] = "classpath:/tempaltes/";
        System.arraycopy(templateLoaderPath, 0, lastPaths, 2, templateLoaderPath.length);
        configurer.setTemplateLoaderPaths(lastPaths);
        configurer.setPreferFileSystemAccess(false);
        Properties settings = new Properties();
        for (Map.Entry<String, String> entry : freeMarkerProperties.getSettings().entrySet()) {
            settings.put(entry.getKey(), entry.getValue());
        }
        configurer.setFreemarkerSettings(settings);
        return configurer;
    }

     /**配置freemarker视图解析器*/
     @Bean
     public ViewResolver freemarkViewResolver(){
         FreeMarkerViewResolver viewResolver = new FreeMarkerViewResolver();
         viewResolver.setViewClass(FreeMarkerView.class);
         viewResolver.setCache(freeMarkerProperties.isCache());
         viewResolver.setSuffix(FreeMarkerProperties.DEFAULT_SUFFIX.equals(freeMarkerProperties.getSuffix())?".html":freeMarkerProperties.getSuffix());
         viewResolver.setContentType(freeMarkerProperties.getContentType().toString());
         viewResolver.setExposeRequestAttributes(freeMarkerProperties.isExposeRequestAttributes());
         viewResolver.setExposeSessionAttributes(freeMarkerProperties.isExposeSessionAttributes());
         viewResolver.setExposeSpringMacroHelpers(freeMarkerProperties.isExposeSpringMacroHelpers());
         viewResolver.setRequestContextAttribute("request");
         viewResolver.setOrder(0);
         return viewResolver;
     }
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.viewResolver(freemarkViewResolver());
    }
}
