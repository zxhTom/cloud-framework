package com.github.zxhtom.login.core.service.impl;

import com.github.zxhtom.login.core.mapper.MenuMapper;
import com.github.zxhtom.login.core.model.Role;
import com.github.zxhtom.login.core.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.login.core.service.impl
 * @date 2021/10/9 14:24
 */
public class MenuServiceImpl implements MenuService {

    @Autowired
    MenuMapper menuMapper;
    @Override
    public List<Role> selectWithRoleBaseOnMenuUrl(String menuUrl) {
        return menuMapper.selectWithRoleBaseOnMenuUrl(menuUrl);
    }

}
