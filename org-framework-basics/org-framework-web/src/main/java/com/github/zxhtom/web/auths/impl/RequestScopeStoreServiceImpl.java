package com.github.zxhtom.web.auths.impl;

import com.github.zxhtom.web.auths.ScopeStoreService;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.web.auths.impl
 * @date 2021/9/30 15:33
 */
@Service
@Scope(value = WebApplicationContext.SCOPE_REQUEST,proxyMode = ScopedProxyMode.TARGET_CLASS)
public class RequestScopeStoreServiceImpl implements ScopeStoreService {
    private String entryPath;
    @Override
    public String getEntryPath() {
        return entryPath;
    }

    @Override
    public void setEntryPath(String entryPath) {
        this.entryPath = entryPath;
    }
}
