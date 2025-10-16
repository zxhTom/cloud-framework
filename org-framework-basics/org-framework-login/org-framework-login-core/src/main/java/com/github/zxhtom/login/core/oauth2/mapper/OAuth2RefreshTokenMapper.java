package com.github.zxhtom.login.core.oauth2.mapper;

import com.github.zxhtom.datasource.mappers.MaltcloudMapper;
import com.github.zxhtom.datasource.query.LambdaQueryWrapperX;
import com.github.zxhtom.login.core.oauth2.dto.OAuth2RefreshTokenDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OAuth2RefreshTokenMapper extends MaltcloudMapper<OAuth2RefreshTokenDO> {

    default int deleteByRefreshToken(String refreshToken) {
        return delete(new LambdaQueryWrapperX<OAuth2RefreshTokenDO>()
                .eq(OAuth2RefreshTokenDO::getRefreshToken, refreshToken));
    }

    default OAuth2RefreshTokenDO selectByRefreshToken(String refreshToken) {
        return selectOne(OAuth2RefreshTokenDO::getRefreshToken, refreshToken);
    }

}
