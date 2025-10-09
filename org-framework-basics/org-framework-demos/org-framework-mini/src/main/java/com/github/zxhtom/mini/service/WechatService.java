package com.github.zxhtom.mini.service;

import com.github.zxhtom.mini.dto.Code2SessionResponse;

/**
 * @author zxhtom
 * 10/3/25
 */
public interface WechatService {

    public Code2SessionResponse code2Session(String code);
}
