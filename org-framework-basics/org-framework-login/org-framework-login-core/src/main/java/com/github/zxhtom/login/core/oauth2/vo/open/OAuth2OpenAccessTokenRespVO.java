package com.github.zxhtom.login.core.oauth2.vo.open;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value = "管理后台 - 【开放接口】访问令牌 Response VO")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OAuth2OpenAccessTokenRespVO {

    @ApiModelProperty(value = "访问令牌", required = true, example = "tudou")
    @JsonProperty("access_token")
    private String accessToken;

    @ApiModelProperty(value = "刷新令牌", required = true, example = "nice")
    @JsonProperty("refresh_token")
    private String refreshToken;

    @ApiModelProperty(value = "令牌类型", required = true, example = "bearer")
    @JsonProperty("token_type")
    private String tokenType;

    @ApiModelProperty(value = "过期时间,单位：秒", required = true, example = "42430")
    @JsonProperty("expires_in")
    private Long expiresIn;

    @ApiModelProperty(value = "授权范围,如果多个授权范围，使用空格分隔", example = "user_info")
    private String scope;

}
