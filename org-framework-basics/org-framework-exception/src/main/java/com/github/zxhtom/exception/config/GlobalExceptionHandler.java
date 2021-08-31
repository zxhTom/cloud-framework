package com.github.zxhtom.exception.config;

import com.github.zxhtom.core.IdGenerator;
import com.github.zxhtom.core.model.SystemExceptionCode;
import com.github.zxhtom.core.service.SystemService;
import com.github.zxhtom.exception.BusinessException;
import com.github.zxhtom.result.unity.ActionResult;
import com.github.zxhtom.result.unity.ActionResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.exception.config
 * @date 2021/8/31 18:49
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    @Autowired
    IdGenerator idGenerator;

    @Autowired
    SystemService systemService;

    @ExceptionHandler(BusinessException.class)
    public ActionResult businessException(BusinessException exception) {
        ActionResult result = new ActionResult(exception.getCode(), exception.getMsg(), null);
        return result;
    }

    /**
    * @author zxhtom
    * @Description 捕获exception异常并将它返回。spirngboot默认将exception进行分装，
     * 如果没有此步骤，在ResponseBodyHandler中将捕获不到exception类型数据,都会认为是正常数据。
     * 此处要么返回exception要么返回处理后统一结构数据
    * @Date 19:11 2021/8/31
    * @Param
    * @return java.lang.Exception
    */
    @ExceptionHandler(Exception.class)
    public ActionResult exception(Exception exception) {
        SystemExceptionCode systemExceptionCode = systemService.selectCodeBaseOnExceptionClass(exception.getClass());
        if (null == systemExceptionCode) {
            return new ActionResult(ActionResultCode.OTHER_ERROR.getValue(), exception.getMessage(), null);
        }
        if (!systemExceptionCode.getCode().toString().startsWith("1")) {
            return new ActionResult(ActionResultCode.OTHER_ERROR.getValue(), String.format("【您配置的异常处理类%s不在规定范围,现已纠偏至-1,请合理配置异常编码为1开头的五位数字  】,真实系统异常信息如下：",exception.getClass().getName())+exception.getMessage(), null);
        }
        return new ActionResult(systemExceptionCode.getCode(), exception.getMessage(), null);
    }
}
