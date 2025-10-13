package com.github.zxhtom.login.core.service.impl;

import com.github.zxhtom.login.core.dto.CombineUser;
import com.github.zxhtom.login.core.model.MiniUser;
import com.github.zxhtom.login.core.model.User;
import com.github.zxhtom.login.core.repository.MiniUserRepository;
import com.github.zxhtom.login.core.service.CloudPassWordEncoder;
import com.github.zxhtom.login.core.service.MiniUserService;
import com.github.zxhtom.login.core.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

/**
 * TODO
 *
 * @author zxhtom
 * 10/9/25
 */
@Service
@Slf4j
public class MiniUserServiceImpl implements MiniUserService {
    @Value("zxhtom.security.user.password:123456")
    String password;
    @Autowired
    MiniUserRepository miniUserRepository;
    @Autowired
    UserService userService;
    @Autowired
    CloudPassWordEncoder cloudPassWordEncoder;
    @Override
    public MiniUser selectMiniUser(String appId, String openId) {
        return miniUserRepository.selectMiniUser(appId,openId);
    }

    @Override
    public CombineUser selectMiniUserOrInitUserWithPrefix(String appId, String openId, String prefix) {
        CombineUser combineUser = new CombineUser();
        MiniUser miniUser = selectMiniUser(appId, openId);
        if (null == miniUser) {
            User user = new User();
            user.setId(System.currentTimeMillis());
            user.setUserName(String.format("%s_%s_%s", prefix, appId, openId));
            user.setUserCode(openId.hashCode());
            user.setPassword(cloudPassWordEncoder.encode(password));
            userService.insertUser(user);
            log.debug("mini user init successful");
            combineUser.setMaltcloud(user);
            miniUser = new MiniUser();
            miniUser.setId(System.currentTimeMillis());
            miniUser.setAppId(appId);
            miniUser.setOpenId(openId);
            miniUser.setUserId(user.getId());
            miniUserRepository.insert(miniUser);
            combineUser.setRegisted(false);
        } else {
            combineUser.setRegisted(true);
            User user = userService.selectUserBaseOnUserId(miniUser.getUserId());
            combineUser.setMaltcloud(user);
        }
        combineUser.setOutUser(miniUser);
        return combineUser;
    }

    @Override
    public CombineUser initMini2Maltcloud(String prefix) {
        return null;
    }

    @Override
    public Integer finishMiniUser(MiniUser miniUser) {
        return miniUserRepository.finishMiniUser(miniUser);
    }
}
