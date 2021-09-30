package com.github.zxhtom.login.security.provider;

import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.login.security.provider
 * @date 2021/9/30 9:48
 */
public class MaltcloudProvider extends DaoAuthenticationProvider {


    public MaltcloudProvider(UserDetailsService userDetailsService) {
        setUserDetailsService(userDetailsService);
        setPasswordEncoder(new BCryptPasswordEncoder());
    }

}
