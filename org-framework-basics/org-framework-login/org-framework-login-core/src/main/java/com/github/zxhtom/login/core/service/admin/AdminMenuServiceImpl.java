package com.github.zxhtom.login.core.service.admin;

import com.github.zxhtom.core.annotaion.SuperDirectionHandler;
import com.github.zxhtom.login.core.model.Menu;
import com.github.zxhtom.login.core.model.Role;
import com.github.zxhtom.login.core.service.MenuService;

import java.util.List;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.login.core.service.admin
 * @date 2021/10/18 14:59
 */
@SuperDirectionHandler
public class AdminMenuServiceImpl implements MenuService {
    @Override
    public List<Role> selectWithRoleBaseOnMenuUrl(String menuUrl) {
        return null;
    }

    @Override
    public List<Menu> selectRootMenuBaseDeviceId(Long moduleId) {
        return null;
    }

    @Override
    public List<Menu> selectChildMenus(Long moduleId, Long parentId) {
        return null;
    }
}
