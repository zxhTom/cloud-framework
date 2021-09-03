package com.github.zxhtom.utils.exception;

import java.io.ByteArrayOutputStream;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.exception.utils
 * @date 2021/9/2 19:49
 */
public class ExceptionUtils {
    private static ExceptionUtils util = new ExceptionUtils();

    public static ExceptionUtils getInstance(){
        return util;
    }

    // 堆栈信息打印方法如下
    public String getThrowableStackInfo(Throwable e) {
        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        e.printStackTrace(new java.io.PrintWriter(buf, true));
        String msg = buf.toString();
        try {
            buf.close();
        } catch (Exception t) {
            return e.getMessage();
        }
        return msg;
    }
}
