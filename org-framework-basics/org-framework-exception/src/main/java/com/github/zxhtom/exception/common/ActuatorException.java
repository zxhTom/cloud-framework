package com.github.zxhtom.exception.common;

import com.github.zxhtom.core.exception.BusinessException;
import com.github.zxhtom.exception.constant.ExceptionConstant;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.exception.common
 * @date 2021/8/31 18:18
 */
public class ActuatorException extends BusinessException {
    public ActuatorException(String message) {
        super(message);
    }

    @Override
    public void setCode(Integer code) {
        super.setCode(ExceptionConstant.ACTUATOR_EXCEPTION_CODE);
    }
}
