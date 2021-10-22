package com.github.zxhtom.login.security.model;

import com.github.zxhtom.web.model.MaltUser;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.login.security.model
 * @date 2021/10/18 15:39
 */
@Getter
@Setter
public class CustomUserDetails extends User implements Serializable {

    private Long deviceId;
    public CustomUserDetails(String username, String password, Long deviceId,Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.deviceId = deviceId;
    }

}
