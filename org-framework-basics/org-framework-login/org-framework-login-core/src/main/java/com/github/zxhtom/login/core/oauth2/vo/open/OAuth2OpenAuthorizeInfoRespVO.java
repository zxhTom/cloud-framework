package com.github.zxhtom.login.core.oauth2.vo.open;

import com.github.zxhtom.core.structs.KeyValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@ApiModel(value = "管理后台 - 授权页的信息 Response VO")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OAuth2OpenAuthorizeInfoRespVO {

    /**
     * 客户端
     */
    private Client client;

    @ApiModelProperty(value = "scope 的选中信息,使用 List 保证有序性，Key 是 scope，Value 为是否选中", required = true)
    private List<KeyValue<String, Boolean>> scopes;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Client {

        @ApiModelProperty(value = "应用名", required = true, example = "土豆")
        private String name;

        @ApiModelProperty(value = "应用图标", required = true, example = "https://www.iocoder.cn/xx.png")
        private String logo;

    }

}
