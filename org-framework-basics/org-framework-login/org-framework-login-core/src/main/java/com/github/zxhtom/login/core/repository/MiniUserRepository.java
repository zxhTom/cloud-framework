package com.github.zxhtom.login.core.repository;

import com.github.zxhtom.login.core.dto.CombineUser;
import com.github.zxhtom.login.core.model.MiniUser;
import com.github.zxhtom.login.core.model.User;

/**
 *
 * @author zxhtom
 * 10/9/25
 */
public interface MiniUserRepository {
    public MiniUser selectMiniUser(String appId, String openId);

    public CombineUser initMini2Maltcloud(String prefix);

    Integer insert(MiniUser miniUser);
}
