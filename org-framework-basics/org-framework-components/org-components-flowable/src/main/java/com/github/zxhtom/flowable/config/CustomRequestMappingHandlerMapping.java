package com.github.zxhtom.flowable.config;

import com.github.zxhtom.flowable.utils.ConverRouteUtil;
import com.github.zxhtom.flowable.utils.ConvertOp;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;

/**
 * TODO
 *
 * @author zxhtom
 * 2023/9/12
 */
public class CustomRequestMappingHandlerMapping extends RequestMappingHandlerMapping {
    @Override
    protected HandlerMethod lookupHandlerMethod(String lookupPath, HttpServletRequest request) throws Exception {
        if(ConverRouteUtil.checkExistCover(lookupPath)){
            String redirectRoute = ConverRouteUtil.getRedirectRoute(lookupPath);
            request.setAttribute("redirectTag","1");
            request.setAttribute("redirectRoute",redirectRoute);
            request.setAttribute("lookupPath",lookupPath);
            lookupPath = redirectRoute;
        }else{
            request.setAttribute("redirectTag","0");
        }
        return super.lookupHandlerMethod(lookupPath, request);
    }

    @Override
    protected RequestMappingInfo getMatchingMapping(RequestMappingInfo info, HttpServletRequest request) {
        String redirectTag = ConvertOp.convert2String(request.getAttribute("redirectTag"));
        if(redirectTag.equals("1")){
            String redirectRoute = ConvertOp.convert2String(request.getAttribute("redirectRoute"));
            boolean check = false;
            if( info.getPatternsCondition()!=null){
                Set<String> set =  info.getPatternsCondition().getPatterns();
                if(set.size()>0){
                    String[] array = new String[set.size()];
                    array = set.toArray(array);
                    String pattern = array[0];
                    if(pattern.equals(redirectRoute)){
                        check = true;
                    }
                }
            }
            if(check){
                return info;
            }else{
                return super.getMatchingMapping(info, request);
            }
        }else{
            return super.getMatchingMapping(info, request);
        }
    }
}
