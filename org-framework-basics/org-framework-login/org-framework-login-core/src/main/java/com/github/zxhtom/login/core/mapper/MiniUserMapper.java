package com.github.zxhtom.login.core.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.zxhtom.login.core.model.MiniUser;
import org.apache.ibatis.annotations.Param;

/**
 * @author zxhtom
 * 10/9/25
 */
public interface MiniUserMapper extends BaseMapper<MiniUser> {
    Integer finishMiniUser(@Param("miniUser") MiniUser miniUser);
}
