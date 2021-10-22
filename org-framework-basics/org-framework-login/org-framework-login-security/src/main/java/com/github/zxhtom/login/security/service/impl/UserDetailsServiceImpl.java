package com.github.zxhtom.login.security.service.impl;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.login.security.service.impl
 * @date 2021/9/30 9:50
 */

import com.github.zxhtom.login.core.model.User;
import com.github.zxhtom.login.core.service.UserService;
import com.github.zxhtom.login.security.model.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userService.selectUserBaseOnUserName(userName);
        if (user == null) {
            throw new UsernameNotFoundException("该用户不存在");
        }
        // 用户权限列表，根据用户拥有的权限标识与如 @PreAuthorize("hasAuthority('sys:menu:view')") 标注的接口对比，决定是否可以调用接口
        Set<String> permissions = userService.selectPermissionsBaseOnUserName(userName);
        List<GrantedAuthority> grantedAuthorities = permissions.stream().map(GrantedAuthorityImpl::new).collect(Collectors.toList());
        return new CustomUserDetails(userName, user.getPassword(), 1L,grantedAuthorities);
    }
}
