package com.github.zxhtom.login.core.service.impl;

import com.github.zxhtom.login.core.mapper.ModuleMapper;
import com.github.zxhtom.login.core.model.Role;
import com.github.zxhtom.login.core.service.MenuService;
import com.github.zxhtom.login.core.service.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.login.core.service.impl
 * @date 2021/10/9 14:24
 */
public class ModuleServiceImpl implements ModuleService {

    @Autowired
    ModuleMapper moduleMapper;
    @Override
    public List<Role> selectWithRoleBaseOnModuleUrl(String moduleUrl) {
        return moduleMapper.selectWithRoleBaseOnModuleUrl(moduleUrl);
    }
}
