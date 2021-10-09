package com.github.zxhtom.login.security.handler.chain;

import com.github.zxhtom.login.core.model.ButtonRoleDto;
import com.github.zxhtom.login.core.model.Role;
import com.github.zxhtom.login.core.service.ButtonService;
import com.github.zxhtom.login.security.handler.AbstractRoleUrlHandler;
import com.github.zxhtom.login.security.handler.HandlerFactory;
import com.github.zxhtom.login.security.model.RequestBaseInfo;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.login.security.handler.chain
 * @date 2021/10/9 14:01
 */
public class ButtonRoleHandler extends AbstractRoleUrlHandler<RequestBaseInfo, List<String>>  {
    @Autowired
    ButtonService buttonService;
    @Override
    public List<String> run(RequestBaseInfo requestBaseInfo) {
        List<ButtonRoleDto> buttonRoleDtos = buttonService.selectButtonMasterAndRoleListBaseBName(requestBaseInfo.getInterfaceUrl());
        for (ButtonRoleDto button : buttonRoleDtos) {
            List<Role> roleList = button.getRoleList();
            if (CollectionUtils.isEmpty(roleList)) {
                continue;
            }
            return roleList.stream().map(Role::getName).distinct().collect(Collectors.toList());
        }
        return null;
    }


}
