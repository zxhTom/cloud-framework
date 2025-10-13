package com.github.zxhtom.mini.service.impl;

import com.github.zxhtom.login.core.model.MiniUser;
import com.github.zxhtom.login.core.model.User;
import com.github.zxhtom.login.core.service.MiniUserService;
import com.github.zxhtom.login.core.service.UserService;
import com.github.zxhtom.mini.dto.ContractUser;
import com.github.zxhtom.mini.service.ContractService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zxhtom
 * 10/6/25
 */
@Service
public class ContractServiceImpl implements ContractService {
    @Autowired
    UserService userService;
    @Autowired
    MiniUserService miniUserService;
    @Override
    public Integer completeProfile(ContractUser user) {
        User sourceUser = new User();
        BeanUtils.copyProperties(user, sourceUser);
        userService.updateActive(sourceUser);
        MiniUser miniUser = new MiniUser();
        miniUser.setUserId(user.getId());
        miniUser.setAppId(user.getAppId());
        miniUser.setFinished(true);
        miniUserService.finishMiniUser(miniUser);
        return null;
    }
}
