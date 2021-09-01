package com.github.zxhtom.core.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.demo.controller
 * @date 2021/8/31 14:32
 */
@Controller
@RequestMapping(value = "/maltcloud")
public class ErrorController {
    @RequestMapping(value = "/error",method = RequestMethod.GET)
    public ModelAndView modelAndView() {
        return new ModelAndView("maltcloud/error");
    }

}
