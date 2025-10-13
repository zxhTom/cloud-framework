package com.github.zxhtom.login.core.mapper;

import com.github.zxhtom.datasource.mappers.MaltcloudMapper;
import com.github.zxhtom.login.core.model.MiniUser;
import org.apache.ibatis.annotations.Param;

/**
 * @author zxhtom
 * 10/9/25
 */
public interface MiniUserMapper extends MaltcloudMapper<MiniUser> {
    Integer finishMiniUser(@Param("miniUser") MiniUser miniUser);
}
