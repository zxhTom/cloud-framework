package com.github.zxhtom.login.core.oauth2.vo.client;

import cn.hutool.core.util.StrUtil;
import com.github.zxhtom.utils.json.JsonUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@ApiModel(value = "管理后台 - OAuth2 客户端创建/修改 Request VO")
@Data
public class OAuth2ClientSaveReqVO {

    @ApiModelProperty(value = "编号", example = "1024")
    private Long id;

    @ApiModelProperty(value = "客户端编号", required = true, example = "tudou")
    @NotNull(message = "客户端编号不能为空")
    private String clientId;

    @ApiModelProperty(value = "客户端密钥", required = true, example = "fan")
    @NotNull(message = "客户端密钥不能为空")
    private String secret;

    @ApiModelProperty(value = "应用名", required = true, example = "土豆")
    @NotNull(message = "应用名不能为空")
    private String name;

    @ApiModelProperty(value = "应用图标", required = true, example = "https://www.iocoder.cn/xx.png")
    @NotNull(message = "应用图标不能为空")
    @URL(message = "应用图标的地址不正确")
    private String logo;

    @ApiModelProperty(value = "应用描述", example = "我是一个应用")
    private String value;

    @ApiModelProperty(value = "状态，参见 CommonStatusEnum 枚举", required = true, example = "1")
    @NotNull(message = "状态不能为空")
    private Integer status;

    @ApiModelProperty(value = "访问令牌的有效期", required = true, example = "8640")
    @NotNull(message = "访问令牌的有效期不能为空")
    private Integer accessTokenValiditySeconds;

    @ApiModelProperty(value = "刷新令牌的有效期", required = true, example = "8640000")
    @NotNull(message = "刷新令牌的有效期不能为空")
    private Integer refreshTokenValiditySeconds;

    @ApiModelProperty(value = "可重定向的 URI 地址", required = true, example = "https://www.iocoder.cn")
    @NotNull(message = "可重定向的 URI 地址不能为空")
    private List<@NotEmpty(message = "重定向的 URI 不能为空") @URL(message = "重定向的 URI 格式不正确") String> redirectUris;

    @ApiModelProperty(value = "授权类型，参见 OAuth2GrantTypeEnum 枚举", required = true, example = "password")
    @NotNull(message = "授权类型不能为空")
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

    @AssertTrue(message = "附加信息必须是 JSON 格式")
    public boolean isAdditionalInformationJson() {
        return StrUtil.isEmpty(additionalInformation) || JsonUtils.getInstance().isJson(additionalInformation);
    }

}
