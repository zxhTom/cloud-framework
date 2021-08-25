package com.github.zxhtom.datasource;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.alibaba.druid.support.spring.stat.DruidStatInterceptor;
import com.github.zxhtom.datasource.constant.DruidConstant;
import com.github.zxhtom.datasource.properties.DruidProperties;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.datasource
 * @date 2021/8/24 16:25
 */
@Configuration
@ImportResource(locations = { "classpath:druid-maltcloud.xml" })
@ServletComponentScan("com.github.zxhtom")
public class DruidConfig {
    @Autowired
    private DruidProperties druidProperties;

    // 注册druid sql分析servlet
    @Bean
    public ServletRegistrationBean druidServlet() {
        ServletRegistrationBean servletBean = new ServletRegistrationBean();
        String username = DruidConstant.USERNAME;
        String password = DruidConstant.PASSWORD;
        boolean enable=druidProperties.isEnable();
        servletBean.setServlet(new StatViewServlet());
        servletBean.setEnabled(true);
        servletBean.addUrlMappings("/druid/*");
        // 获取配置文件中的用户名和密码 没有配置则注入默认用户名和密码
        if (StringUtils.isNoneEmpty(druidProperties.getUsername())
                && StringUtils.isNoneEmpty(druidProperties.getPassword())) {
            username = druidProperties.getUsername();
            password = druidProperties.getPassword();
        }
        if(enable){
            servletBean.addInitParameter("loginUsername", username);
            servletBean.addInitParameter("loginPassword", password);
        }
        // 白名单
        servletBean.addInitParameter("allow", "");
        // 黑名单
        servletBean.addInitParameter("deny", "");
        // 禁用html页面行的reset all功能
        servletBean.addInitParameter("resetEnable", "false");
        return servletBean;
        // 可以完全由下面一段替换
        // return new ServletRegistrationBean(new StatViewServlet(),
        // "/druid/*");
    }
    @Bean
    public WebStatFilter getWebStatFilter(){
        WebStatFilter webStatFilter = new WebStatFilter();
        webStatFilter.setProfileEnable(true);
        webStatFilter.setSessionStatEnable(true);
        return webStatFilter;
    }
    // 注册druid sql分析filter
    @Bean
    public FilterRegistrationBean druidFilter(WebStatFilter webStatFilter) {
        FilterRegistrationBean filterBean = new FilterRegistrationBean();
        filterBean.setFilter(webStatFilter);
        filterBean.setEnabled(true);
        filterBean.addUrlPatterns("/*");
        filterBean.addInitParameter("exclusions", "*.js,*.pdf,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        return filterBean;
    }
}
