package com.github.zxhtom.common.controller;

import com.github.zxhtom.common.service.CommonSystemService;
import com.github.zxhtom.core.annotaion.SuperDirection;
import com.github.zxhtom.core.annotaion.SuperDirectionHandler;
import com.github.zxhtom.login.core.dto.MenuDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
@RequestMapping("/framework_common")
public class SystemController {

    @Autowired
    CommonSystemService commonSystemService;

    @RequestMapping(value = "/menu_init/nav", method = RequestMethod.GET)
    public List<MenuDto> selectMenuListByLoginUser(@RequestParam Long moduleId) {
        return commonSystemService.selectMenuListByLoginUser(moduleId);
    }

    @RequestMapping(value = "/user/info", method = RequestMethod.GET)
    public Object userInfo() {
        return commonSystemService.userInfo();
    }
}
