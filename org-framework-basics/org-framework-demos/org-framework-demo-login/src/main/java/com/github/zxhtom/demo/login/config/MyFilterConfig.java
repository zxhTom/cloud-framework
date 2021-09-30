package com.github.zxhtom.demo.login.config;

import com.github.zxhtom.demo.login.filters.MyFilter1;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.login.security.config
 * @date 2021/9/22 13:15
 */
@Configuration
public class MyFilterConfig {
    //@Bean
    public FilterRegistrationBean myFilter(MyFilter1 myFilter1){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(myFilter1);
        filterRegistrationBean.setUrlPatterns(Arrays.asList("/test/*"));
        return filterRegistrationBean;
    }

}
