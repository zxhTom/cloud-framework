package com.github.zxhtom.login.core.oauth2.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.zxhtom.datasource.mappers.MaltcloudMapper;
import com.github.zxhtom.datasource.query.LambdaQueryWrapperX;
import com.github.zxhtom.login.core.oauth2.dto.OAuth2ClientDO;
import com.github.zxhtom.login.core.oauth2.vo.client.OAuth2ClientPageReqVO;
import org.apache.ibatis.annotations.Mapper;


/**
 * OAuth2 客户端 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface OAuth2ClientMapper extends MaltcloudMapper<OAuth2ClientDO> {

    default Page<OAuth2ClientDO> selectPage(OAuth2ClientPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<OAuth2ClientDO>()
                .likeIfPresent(OAuth2ClientDO::getName, reqVO.getName())
                .eqIfPresent(OAuth2ClientDO::getStatus, reqVO.getStatus())
                .orderByDesc(OAuth2ClientDO::getId));
    }

    default OAuth2ClientDO selectByClientId(String clientId) {
        return selectOne(OAuth2ClientDO::getClientId, clientId);
    }

}
