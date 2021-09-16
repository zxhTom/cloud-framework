package com.github.zxhtom.demo.freemark.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.demo.freemark.controller
 * @date 2021/9/16 9:44
 */
@Controller
public class IndexController {

    @RequestMapping(value = "/success",method = RequestMethod.GET)
    public ModelAndView  success() {
        Map<String, String> user = new HashMap<>();
        user.put("name", "zxhtom");
        ModelAndView mv = new ModelAndView();
        mv.addObject("user", user);
        mv.setViewName("success");
        return mv;
    }
}
