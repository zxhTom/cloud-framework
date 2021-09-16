package com.github.zxhtom.demo.login;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.demo.login
 * @date 2021/9/15 13:21
 */
public class PasswordTest {
    @Test
    public void encode() {
        String password = "123456";
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encode = bCryptPasswordEncoder.encode(password);
        System.out.println(encode);
    }
}
