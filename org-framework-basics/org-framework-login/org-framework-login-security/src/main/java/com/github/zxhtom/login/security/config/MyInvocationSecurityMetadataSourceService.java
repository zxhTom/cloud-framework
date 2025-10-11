package com.github.zxhtom.login.security.config;


import com.github.zxhtom.login.core.model.PermissionUrl;
import com.github.zxhtom.login.core.service.ButtonService;
import com.github.zxhtom.login.core.service.PermissionUrlService;
import com.github.zxhtom.login.security.handler.AbastractPathAttributeHandler;
import com.github.zxhtom.login.security.handler.HandlerFactory;
import com.github.zxhtom.login.security.model.RequestBaseInfo;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.demo.login.config
 * @date 2021/10/8 14:05
 */
@Component
public class MyInvocationSecurityMetadataSourceService  implements
        FilterInvocationSecurityMetadataSource {

    @Autowired
    ButtonService buttonService;

    @Autowired
    PermissionUrlService permissionUrlService;

    private List<AbastractPathAttributeHandler> ahList=new ArrayList<>();

    private HashMap<String, Collection<ConfigAttribute>> map =new HashMap<>();

    public void registerHandler(AbastractPathAttributeHandler handler) {
        if (null != handler) {
            ahList.add(handler);
        }
    }
    /**
     * 加载权限表中所有权限
     * TODO 此方法待分布式锁完成后接入并将数据缓存
     */
    public void loadResourceDefine(Object object){
        HttpServletRequest request = ((FilterInvocation) object).getHttpRequest();
        String requestURI = request.getRequestURI();
        Collection<ConfigAttribute> array = new ArrayList<>();;
        ConfigAttribute cfg;
        RequestBaseInfo info = new RequestBaseInfo();
        info.setInterfaceUrl(requestURI);
        List<String> roleList = (List<String>) HandlerFactory.getInstance().chain(RequestBaseInfo.class).execute(info);
        map.clear();
        if (CollectionUtils.isEmpty(roleList)) {
            return ;
        }
        for (String objName : roleList) {
            cfg = new org.springframework.security.access.SecurityConfig(objName);
            //此处只添加了用户的名字，其实还可以添加更多权限的信息，例如请求方法到ConfigAttribute的集合中去。此处添加的信息将会作为MyAccessDecisionManager类的decide的第三个参数。
            array.add(cfg);
        }
        //用权限的getUrl() 作为map的key，用ConfigAttribute的集合作为 value，
        map.put(requestURI, array);
    }

    //此方法是为了判定用户请求的url 是否在权限表中，如果在权限表中，则返回给 decide 方法，用来判定用户是否有此权限。如果不在权限表中则放行。
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        //object 中包含用户请求的request 信息
        HttpServletRequest request = ((FilterInvocation) object).getHttpRequest();
        if (request.getRequestURI().contains(".")) {
            return null;
        }
        if (new AntPathRequestMatcher("/**/error").matches(request)) {
            return null;
        }
        loadResourceDefine(object);
        AntPathRequestMatcher matcher;
        String resUrl;
        for(Iterator<String> iter = map.keySet().iterator(); iter.hasNext(); ) {
            resUrl = iter.next();
            matcher = new AntPathRequestMatcher(resUrl);
            if(matcher.matches(request)) {
                return map.get(resUrl);
            }
        }

        List<PermissionUrl> permissionUrls = permissionUrlService.selectAllPermissionUrls();
        for (PermissionUrl permissionUrl : permissionUrls) {
            if (new AntPathRequestMatcher(permissionUrl.getUrlPattern()).matches(request)) {
                //匹配到路由
                for (AbastractPathAttributeHandler handler : ahList) {
                    if (!handler.support(permissionUrl)) {
                        continue;
                    }
                    return handler.handle(permissionUrl);
                }
            }
        }
        throw new AccessDeniedException("未监测到权限配置，安全起见禁止访问");
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}

