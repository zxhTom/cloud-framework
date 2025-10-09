package com.github.zxhtom.mini.dto;

import lombok.Data;

/**
 * TODO
 *
 * @author zxhtom
 * 10/3/25
 */

// 定义接收微信响应的内部类
@Data
public class Code2SessionResponse {
    private String openid;      // 用户唯一标识
    private String session_key; // 会话密钥
    private String unionid;     // 可选，在同一开放平台账号下的统一ID
    private Integer errcode;    // 错误码
    private String errmsg;      // 错误信息

    public boolean isSuccess() {
        return errcode == null || errcode == 0;
    }
}
