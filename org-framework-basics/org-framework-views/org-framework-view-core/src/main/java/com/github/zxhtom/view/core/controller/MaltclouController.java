package com.github.zxhtom.view.core.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.view.core.controller
 * @date 2021/9/17 19:48
 */
@Controller
public class MaltclouController {
    @RequestMapping(value = "/**/{viewName}.html2")
    public String viewName(@PathVariable String viewName, HttpServletRequest request) {
        /*
         * try { response.sendRedirect("system.html"); } catch (Exception e) { }
         */
        viewName = request.getServletPath();
        if (viewName.indexOf(".") != -1) {
            viewName = viewName.substring(1, viewName.indexOf("."));
        }
        return viewName;
    }
}
