package com.github.zxhtom.login.core.service;

import com.github.zxhtom.login.core.dto.CombineUser;
import com.github.zxhtom.login.core.model.MiniUser;

/**
 *
 * @author zxhtom
 * 10/9/25
 */
public interface MiniUserService {

    public MiniUser selectMiniUser(String appId, String openId);

    public CombineUser selectMiniUserOrInitUserWithPrefix(String appId, String openId,String prefix);

    public CombineUser initMini2Maltcloud(String prefix);

    Integer finishMiniUser(MiniUser miniUser);
}
