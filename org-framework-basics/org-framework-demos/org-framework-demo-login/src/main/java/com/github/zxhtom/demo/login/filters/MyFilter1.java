package com.github.zxhtom.demo.login.filters;

import com.github.zxhtom.demo.login.controller.TestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.demo.login.filters
 * @date 2021/9/23 9:46
 */
//@Component
public class MyFilter1 extends GenericFilterBean {
    /*@Autowired
    TestController testController;*/
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("111111111111");
        chain.doFilter(request,response);
    }
}
