package com.github.zxhtom.core.config;

import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.ErrorPageRegistrar;
import org.springframework.boot.web.server.ErrorPageRegistry;
import org.springframework.http.HttpStatus;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.core
 * @date 2021/9/1 13:48
 */
public class ErrorConfig implements ErrorPageRegistrar {
    @Override
    public void registerErrorPages(ErrorPageRegistry registry) {
        /*1、按错误的类型显示错误的网页*/
        /*错误类型为404，找不到网页的，默认显示404.html网页*/
        ErrorPage e404 = new ErrorPage(HttpStatus.NOT_FOUND, "/maltcloud/error");
        //错误类型为500，表示服务器响应错误，默认显示500.html网页
        ErrorPage e500 = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/maltcloud/error");
        registry.addErrorPages(e404);
    }
}
