package com.github.zxhtom.login.core.oauth2.mapper;

import com.github.zxhtom.datasource.mappers.MaltcloudMapper;
import com.github.zxhtom.login.core.oauth2.dto.OAuth2CodeDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OAuth2CodeMapper extends MaltcloudMapper<OAuth2CodeDO> {

    default OAuth2CodeDO selectByCode(String code) {
        return selectOne(OAuth2CodeDO::getCode, code);
    }

}
