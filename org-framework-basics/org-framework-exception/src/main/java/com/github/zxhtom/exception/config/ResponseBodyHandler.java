package com.github.zxhtom.exception.config;

import com.github.zxhtom.core.IdGenerator;
import com.github.zxhtom.exception.BusinessException;
import com.github.zxhtom.exception.constant.ExceptionConstant;
import com.github.zxhtom.result.annotation.ProtoResult;
import com.github.zxhtom.result.unity.ActionResult;
import com.github.zxhtom.result.unity.ActionResultCode;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.demo.config
 * @date 2021/8/31 15:38
 */
@ControllerAdvice
public class ResponseBodyHandler implements ResponseBodyAdvice<Object> {
    @Autowired
    IdGenerator idGenerator;
    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        HttpServletRequest request = ((ServletServerHttpRequest) serverHttpRequest).getServletRequest();
        String instanceId = StringUtils.EMPTY;
        Object instanceIdAttribute = request.getAttribute(ExceptionConstant.INSTANCE_ID);
        if (null != instanceIdAttribute) {
            instanceId = instanceIdAttribute.toString();
        }else{
            instanceId = idGenerator.generateAndGetId();
        }
        if (body == null) {
            return new ActionResult(ActionResultCode.SUCCESS.getValue(),instanceId, null, null);
        }
        ProtoResult annotation = methodParameter.getContainingClass().getAnnotation(ProtoResult.class);
        if (annotation!=null) {
            /**用于对外提供原生数据；快速响应*/
            return body;
        }
        if (body instanceof ActionResult) {
            ActionResult result = (ActionResult) body;
            result.setInstanceId(instanceId);
            return result;
        }
        if (body instanceof Exception) {
            System.out.println(body.getClass());
        }
        ActionResult result = new ActionResult(ActionResultCode.SUCCESS.getValue(),instanceId, null, body);
        return result;
    }
}
