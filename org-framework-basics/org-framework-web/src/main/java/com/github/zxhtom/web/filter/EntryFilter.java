package com.github.zxhtom.web.filter;

import com.github.zxhtom.web.auths.OnlineSecurity;
import com.github.zxhtom.web.auths.ScopeStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.web.filter
 * @date 2021/9/30 14:41
 */
@Component
public class EntryFilter implements Filter {
    @Autowired
    private ScopeStoreService scopeStoreService;
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String servletPath = ((HttpServletRequest) request).getServletPath();
        scopeStoreService.setEntryPath(servletPath);
        chain.doFilter(request,response);
    }
}
