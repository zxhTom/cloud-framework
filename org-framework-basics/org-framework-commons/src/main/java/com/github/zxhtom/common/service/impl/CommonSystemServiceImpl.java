package com.github.zxhtom.common.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.zxhtom.common.service.CommonSystemService;
import com.github.zxhtom.login.core.dto.MenuDto;
import com.github.zxhtom.login.core.model.Menu;
import com.github.zxhtom.login.core.service.MenuService;
import com.github.zxhtom.utils.json.JsonUtils;
import com.github.zxhtom.web.auths.OnlineSecurity;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.common.service.impl
 * @date 2021/10/12 16:24
 */
@Service
public class CommonSystemServiceImpl implements CommonSystemService {
    @Autowired
    OnlineSecurity onlineSecurity;
    @Autowired
    MenuService menuService;
    @Override
    public List<MenuDto> selectMenuListByLoginUser(Long moduleId) {
        return selectDigui(moduleId,-1L);
    }

    @Override
    public Object userInfo() {
        return onlineSecurity.getOnlinePrincipal();
    }

    private List<MenuDto> selectDigui(Long moduleId,Long id) {
        List<MenuDto> dtoList = new ArrayList<>();
        List<Menu> menuList = menuService.selectChildMenus(moduleId,id);
        if (CollectionUtils.isNotEmpty(menuList)) {
            for (Menu menu : menuList) {
                MenuDto dto = new MenuDto();
                BeanUtils.copyProperties(menu, dto);
                List<MenuDto> menuDtoList = selectDigui(moduleId,menu.getId());
                dto.setMenuList(menuDtoList);
                dtoList.add(dto);
            }
        }
        return dtoList;

    }
}
