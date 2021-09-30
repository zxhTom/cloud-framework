package com.github.zxhtom.demo.login.filters;

import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.*;
import java.io.IOException;
import java.util.List;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.demo.login.filters
 * @date 2021/9/23 9:44
 */
public class MyDelegatingFiltetrProxy extends GenericFilterBean {
    List<Filter> filterList ;

    @Override
    protected void initFilterBean() throws ServletException {
        filterList.add(new MyFilter1());
        filterList.add(new MyFilter2());
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        for (Filter filter : filterList) {
            filter.doFilter(request, response, chain);
        }
    }
}
