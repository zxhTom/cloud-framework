package com.github.zxhtom.demo.login.filters;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.demo.login.filters
 * @date 2021/9/23 9:46
 */
public class MyFilter2 implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("222222222");
    }
}
