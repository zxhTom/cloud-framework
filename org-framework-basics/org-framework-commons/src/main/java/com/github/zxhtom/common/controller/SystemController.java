package com.github.zxhtom.common.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.common.controller
 * @date 2021/10/11 15:30
 */
@RestController()
@RequestMapping("/framework_common/menu_init")
public class SystemController {

    @RequestMapping(value = "/nav", method = RequestMethod.GET)
    public void selectMenuListByLoginUser(HttpServletRequest request) {

    }
}
