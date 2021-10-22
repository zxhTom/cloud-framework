package com.github.zxhtom.common.service;

import com.github.zxhtom.core.datasouce.PagedResult;
import com.github.zxhtom.login.core.model.User;

import java.util.List;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.common.service
 * @date 2021/10/22 11:18
 */
public interface CommonUserService {
    PagedResult<User> selectSysUsersByPK(Long userId, Integer pageNumber, Integer pageSize);

    Integer insertUpdate(List<User> users);

    Integer deleteBatch(List<Long> ids);
}
