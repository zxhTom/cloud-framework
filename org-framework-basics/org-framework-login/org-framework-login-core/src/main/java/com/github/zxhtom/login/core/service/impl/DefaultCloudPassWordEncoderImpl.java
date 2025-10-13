package com.github.zxhtom.login.core.service.impl;

import com.github.zxhtom.login.core.service.CloudPassWordEncoder;

/**
 * @author zxhtom
 * 10/12/25
 */
public class DefaultCloudPassWordEncoderImpl implements CloudPassWordEncoder {
    @Override
    public String encode(String source) {
        return source;
    }
}
