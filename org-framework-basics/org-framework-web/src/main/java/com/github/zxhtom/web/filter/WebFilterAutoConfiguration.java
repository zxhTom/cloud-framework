package com.github.zxhtom.web.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.web.filter
 * @date 2021/9/30 14:46
 */
@Configuration
public class WebFilterAutoConfiguration{
    @Bean
    public FilterRegistrationBean entryFilter(EntryFilter entryFilter){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(entryFilter);
        filterRegistrationBean.setUrlPatterns(Arrays.asList("/*"));
        filterRegistrationBean.setOrder(1);
        return filterRegistrationBean;
    }
}
