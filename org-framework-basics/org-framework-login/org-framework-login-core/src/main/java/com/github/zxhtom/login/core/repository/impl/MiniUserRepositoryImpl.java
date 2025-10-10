package com.github.zxhtom.login.core.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.zxhtom.core.exception.BusinessException;
import com.github.zxhtom.login.core.dto.CombineUser;
import com.github.zxhtom.login.core.mapper.MiniUserMapper;
import com.github.zxhtom.login.core.model.MiniUser;
import com.github.zxhtom.login.core.repository.MiniUserRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author zxhtom
 * 10/9/25
 */
@Repository
public class MiniUserRepositoryImpl implements MiniUserRepository {
    @Autowired
    MiniUserMapper miniUserMapper;
    @Override
    public MiniUser selectMiniUser(String appId, String openId) {
        if (StringUtils.isEmpty(appId)) {
            throw new BusinessException("appId is empty");
        }
        if (StringUtils.isEmpty(openId)) {
            throw new BusinessException("openId is empty");
        }
        QueryWrapper<MiniUser> miniUserQueryWrapper = new QueryWrapper<>();
        miniUserQueryWrapper.eq("app_id", appId);
        miniUserQueryWrapper.eq("open_id", openId);
        return miniUserMapper.selectOne(miniUserQueryWrapper);
    }

    @Override
    public CombineUser initMini2Maltcloud(String prefix) {
        return null;
    }

    @Override
    public Integer insert(MiniUser miniUser) {
        return miniUserMapper.insert(miniUser);
    }
}
