package com.github.zxhtom.login.core.oauth2.vo.token;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@ApiModel(value = "管理后台 - 访问令牌 Response VO")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OAuth2AccessTokenRespVO {

    @ApiModelProperty(value = "编号", required = true, example = "1024")
    private Long id;

    @ApiModelProperty(value = "访问令牌", required = true, example = "tudou")
    private String accessToken;

    @ApiModelProperty(value = "刷新令牌", required = true, example = "nice")
    private String refreshToken;

    @ApiModelProperty(value = "用户编号", required = true, example = "666")
    private Long userId;

    @ApiModelProperty(value = "用户类型，参见 UserTypeEnum 枚举", required = true, example = "2")
    private Integer userType;

    @ApiModelProperty(value = "客户端编号", required = true, example = "2")
    private String clientId;

    @ApiModelProperty(value = "创建时间", required = true)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "过期时间", required = true)
    private LocalDateTime expiresTime;

}
