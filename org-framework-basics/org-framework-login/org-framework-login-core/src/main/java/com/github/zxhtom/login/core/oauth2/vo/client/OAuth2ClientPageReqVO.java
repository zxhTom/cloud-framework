package com.github.zxhtom.login.core.oauth2.vo.client;

import com.github.zxhtom.core.datasouce.PagedRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("管理后台 - OAuth2 客户端创建/修改 Request VO")
public class OAuth2ClientPageReqVO extends PagedRequest {

    @ApiModelProperty(value = "应用名，模糊匹配", example = "土豆")
    private String name;

    @ApiModelProperty(value = "状态，参见 CommonStatusEnum 枚举", example = "1")
    private Integer status;

}
