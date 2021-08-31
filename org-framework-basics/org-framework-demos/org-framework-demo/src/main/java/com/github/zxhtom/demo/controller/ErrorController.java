package com.github.zxhtom.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.demo.controller
 * @date 2021/8/31 14:32
 */
@RestController
public class ErrorController {
    @RequestMapping(value = "/error2",method = RequestMethod.GET)
    public ModelAndView modelAndView() {
        return new ModelAndView("error");
    }

}
