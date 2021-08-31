package com.github.zxhtom.exception.constant;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.exception.constant
 * @date 2021/8/31 16:52
 */
public class ExceptionConstant {
    public final static String INSTANCE_ID = "exception.instance.id";
    /**用户类异常*/
    public final static Integer USER_EXCEPTION_CODE = 20001;
    /**权限类异常，用户权限异常放在用户类异常中*/
    public final static Integer ACCESS_EXCEPTION_CODE = 30001;
    /**监控类异常*/
    public final static Integer ACTUATOR_EXCEPTION_CODE = 40001;
    /**数据填充类异常*/
    public final static Integer FILL_EXCEPTION_CODE = 50001;
    /**消息异常*/
    public final static Integer MSG_EXCEPTION_CODE = 60001;
    /**数据验证异常*/
    public final static Integer VALID_EXCEPTION_CODE = 70001;
    /**删除数据异常*/
    public final static Integer DELETE_EXCEPTION_CODE = 80001;
}
