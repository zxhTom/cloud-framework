package com.github.zxhtom.login.core.mapper;

import com.github.zxhtom.login.core.model.Button;
import com.github.zxhtom.login.core.model.ButtonRoleDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.login.security.mapper
 * @date 2021/10/8 14:32
 */
public interface ButtonMapper {
    public List<Button> selectButtonList();

    public List<ButtonRoleDto> selectButtonMasterAndRoleListBaseBUrl(@Param("buttonUrl") String buttonUrl);


}
