package com.github.zxhtom.flowable.controller;

import com.alibaba.fastjson.JSON;
import com.github.zxhtom.flowable.model.FlowableIdmAppUserModel;
import org.flowable.idm.api.IdmIdentityService;
import org.flowable.ui.common.model.ResultListDataRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * TODO
 *
 * @author zxhtom
 * 2023/9/12
 */
//@WebServlet(name = "myServlet",urlPatterns = "")
public class ModerlerServlet extends HttpServlet {

    @Autowired
    IDMAppController idmAppController;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String filter = req.getParameter("filter");
        ResultListDataRepresentation users = idmAppController.getUsers(filter);
        System.out.println("servlet doGet method called...");
        resp.getWriter().println(JSON.toJSONString(users));
        resp.getWriter().flush();
        resp.getWriter().close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("servlet doPost method called...");
        resp.getWriter().println("doPost method called url is ["+req.getRequestURL()+"] time is ["+System.currentTimeMillis()+"]");
        resp.getWriter().flush();
        resp.getWriter().close();
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
//        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
//        ServletContext servletContext = this.getServletContext();
//        WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
//        idmAppController = webApplicationContext.getBean(IDMAppController.class);
    }
}
