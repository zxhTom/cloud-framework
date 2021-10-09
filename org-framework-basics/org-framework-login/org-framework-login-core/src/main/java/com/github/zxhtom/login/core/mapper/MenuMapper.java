package com.github.zxhtom.login.core.mapper;


import com.github.zxhtom.login.core.model.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.login.core.mapper
 * @date 2021/10/9 11:01
 */
public interface MenuMapper {
    List<Role> selectWithRoleBaseOnMenuUrl(@Param("url") String url);
}
