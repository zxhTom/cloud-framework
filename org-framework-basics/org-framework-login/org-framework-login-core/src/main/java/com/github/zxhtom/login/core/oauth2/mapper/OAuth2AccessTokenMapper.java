package com.github.zxhtom.login.core.oauth2.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.zxhtom.datasource.mappers.MaltcloudMapper;
import com.github.zxhtom.datasource.query.LambdaQueryWrapperX;
import com.github.zxhtom.login.core.oauth2.dto.OAuth2AccessTokenDO;
import com.github.zxhtom.login.core.oauth2.vo.token.OAuth2AccessTokenPageReqVO;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface OAuth2AccessTokenMapper extends MaltcloudMapper<OAuth2AccessTokenDO> {

    default OAuth2AccessTokenDO selectByAccessToken(String accessToken) {
        return selectOne(OAuth2AccessTokenDO::getAccessToken, accessToken);
    }

    default List<OAuth2AccessTokenDO> selectListByRefreshToken(String refreshToken) {
        return selectList(OAuth2AccessTokenDO::getRefreshToken, refreshToken);
    }

    default Page<OAuth2AccessTokenDO> selectPage(OAuth2AccessTokenPageReqVO reqVO) {
        LambdaQueryWrapper<OAuth2AccessTokenDO> oAuth2AccessTokenDOLambdaQueryWrapper = new LambdaQueryWrapperX<OAuth2AccessTokenDO>()
                .eqIfPresent(OAuth2AccessTokenDO::getUserId, reqVO.getUserId())
                .likeIfPresent(OAuth2AccessTokenDO::getClientId, reqVO.getClientId())
                .gt(OAuth2AccessTokenDO::getExpiresTime, LocalDateTime.now())
                .orderByDesc(OAuth2AccessTokenDO::getId);
        return selectPage(reqVO, oAuth2AccessTokenDOLambdaQueryWrapper);
    }

}
