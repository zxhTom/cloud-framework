package com.github.zxhtom.login.core.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.zxhtom.core.annotaion.SuperDirection;
import com.github.zxhtom.core.exception.BusinessException;
import com.github.zxhtom.login.core.mapper.MenuMapper;
import com.github.zxhtom.login.core.model.Menu;
import com.github.zxhtom.login.core.model.Role;
import com.github.zxhtom.login.core.service.MenuService;
import com.github.zxhtom.utils.json.JsonUtils;
import com.github.zxhtom.web.auths.OnlineSecurity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.login.core.service.impl
 * @date 2021/10/9 14:24
 */
@SuperDirection
public class MenuServiceImpl implements MenuService {

    @Autowired
    OnlineSecurity onlineSecurity;
    @Autowired
    MenuMapper menuMapper;
    @Override
    public List<Role> selectWithRoleBaseOnMenuUrl(String menuUrl) {
        return menuMapper.selectWithRoleBaseOnMenuUrl(menuUrl);
    }

    @Override
    public List<Menu> selectRootMenuBaseDeviceId(Long moduleId) {
        String deviceId = StringUtils.EMPTY;
        Object onlinePrincipal = onlineSecurity.getOnlinePrincipal();
        if (JsonUtils.getInstance().isJson(JSON.toJSONString(onlinePrincipal))) {
            JSONObject jsonOnlinePrincipal = (JSONObject) JSONObject.parse(JSON.toJSONString(onlinePrincipal));
            deviceId = jsonOnlinePrincipal.getString("deviceId");
        }
        if (StringUtils.isEmpty(deviceId)) {
            throw new BusinessException("deviceId在普通模式下禁止传空");
        }

        return menuMapper.selectRootMenuWithInDeviceId(moduleId,deviceId,-1L);
    }

    @Override
    public List<Menu> selectChildMenus(Long moduleId, Long parentId) {
        String deviceId = StringUtils.EMPTY;
        Object onlinePrincipal = onlineSecurity.getOnlinePrincipal();
        if (JsonUtils.getInstance().isJson(JSON.toJSONString(onlinePrincipal))) {
            JSONObject jsonOnlinePrincipal = (JSONObject) JSONObject.parse(JSON.toJSONString(onlinePrincipal));
            deviceId = jsonOnlinePrincipal.getString("deviceId");
        }
        if (StringUtils.isEmpty(deviceId)) {
            throw new BusinessException("deviceId在普通模式下禁止传空");
        }

        return menuMapper.selectRootMenuWithInDeviceId(moduleId,deviceId,parentId);
    }

}
