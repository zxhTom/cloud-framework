package com.github.zxhtom.login.security.service.impl;

import com.github.zxhtom.login.core.service.CloudPassWordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author zxhtom
 * 10/12/25
 */
@Service
public class SecurityCloudPassWordEncoderImpl implements CloudPassWordEncoder {
    @Autowired
    @Lazy
    PasswordEncoder passwordEncoder;
    @Override
    public String encode(String source) {
        return passwordEncoder.encode(source);
    }
}
