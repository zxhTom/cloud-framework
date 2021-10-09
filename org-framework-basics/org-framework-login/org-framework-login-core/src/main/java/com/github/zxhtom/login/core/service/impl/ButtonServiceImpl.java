package com.github.zxhtom.login.core.service.impl;

import com.github.zxhtom.login.core.mapper.ButtonMapper;
import com.github.zxhtom.login.core.model.Button;
import com.github.zxhtom.login.core.model.ButtonRoleDto;
import com.github.zxhtom.login.core.service.ButtonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.login.security.service.impl
 * @date 2021/10/8 14:39
 */
@Service
public class ButtonServiceImpl implements ButtonService{
    @Autowired
    ButtonMapper buttonMapper;
    @Override
    public List<Button> selectList() {
        return buttonMapper.selectButtonList();
    }

    @Override
    public List<ButtonRoleDto> selectButtonMasterAndRoleListBaseBName(String buttonUrl) {
        return buttonMapper.selectButtonMasterAndRoleListBaseBUrl(buttonUrl);
    }
}
