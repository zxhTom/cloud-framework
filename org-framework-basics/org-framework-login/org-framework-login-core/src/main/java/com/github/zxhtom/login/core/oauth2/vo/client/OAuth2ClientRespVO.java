package com.github.zxhtom.login.core.oauth2.vo.client;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@ApiModel(value = "管理后台 - OAuth2 客户端 Response VO")
@Data
public class OAuth2ClientRespVO {

    @ApiModelProperty(value = "编号", required = true, example = "1024")
    private Long id;

    @ApiModelProperty(value = "客户端编号", required = true, example = "tudou")
    private String clientId;

    @ApiModelProperty(value = "客户端密钥", required = true, example = "fan")
    private String secret;

    @ApiModelProperty(value = "应用名", required = true, example = "土豆")
    private String name;

    @ApiModelProperty(value = "应用图标", required = true, example = "https://www.iocoder.cn/xx.png")
    private String logo;

    @ApiModelProperty(value = "应用描述", example = "我是一个应用")
    private String value;

    @ApiModelProperty(value = "状态，参见 CommonStatusEnum 枚举", required = true, example = "1")
    private Integer status;

    @ApiModelProperty(value = "访问令牌的有效期", required = true, example = "8640")
    private Integer accessTokenValiditySeconds;

    @ApiModelProperty(value = "刷新令牌的有效期", required = true, example = "8640000")
    private Integer refreshTokenValiditySeconds;

    @ApiModelProperty(value = "可重定向的 URI 地址", required = true, example = "https://www.iocoder.cn")
    private List<String> redirectUris;

    @ApiModelProperty(value = "授权类型，参见 OAuth2GrantTypeEnum 枚举", required = true, example = "password")
    private List<String> authorizedGrantTypes;

    @ApiModelProperty(value = "授权范围", example = "user_info")
    private List<String> scopes;

    @ApiModelProperty(value = "自动通过的授权范围", example = "user_info")
    private List<String> autoApproveScopes;

    @ApiModelProperty(value = "权限", example = "system:user:query")
    private List<String> authorities;

    @ApiModelProperty(value = "资源", example = "1024")
    private List<String> resourceIds;

    @ApiModelProperty(value = "附加信息", example = "{yunai: true}")
    private String additionalInformation;

    @ApiModelProperty(value = "创建时间", required = true)
    private LocalDateTime createTime;

}
