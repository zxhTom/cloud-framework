package com.github.zxhtom.common.service.impl;

import com.github.zxhtom.common.repository.CommonUserRepository;
import com.github.zxhtom.common.service.CommonUserService;
import com.github.zxhtom.core.datasouce.PagedResult;
import com.github.zxhtom.core.exception.BusinessException;
import com.github.zxhtom.core.utils.PagegationUtils;
import com.github.zxhtom.login.core.model.User;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.common.service.impl
 * @date 2021/10/22 11:18
 */
@Service
public class CommonUserServiceImpl implements CommonUserService {

    @Autowired
    CommonUserRepository commonUserRepository;
    @Override
    public PagedResult<User> selectSysUsersByPK(Long userId, Integer pageNumber, Integer pageSize) {
        PagedResult<User> result = new PagedResult(pageNumber,pageSize);
        Integer pageStart = PagegationUtils.getInstance().pageStart(pageNumber,pageSize);
        if (userId == 0) {
            //查询权限下所有 return;
            List<User> userList = commonUserRepository.selectUserAllWithAccess(pageStart,pageSize);
            Long count = commonUserRepository.selectUserCountAllWithAccess(pageStart,pageSize);
            PagegationUtils.getInstance().initOthers3P(result,userList,count);
            return result;
        }
        //查询指定id用户数据；需要验证是否有权限查询
        List<User> userList = commonUserRepository.selectUserWithUserIdAccess(userId,pageStart,pageSize);
        Long count = commonUserRepository.selectUserCountWithUserIdAccess(userId,pageStart,pageSize);
        PagegationUtils.getInstance().initOthers3P(result,userList,count);
        return result;
    }

    @Override
    public Integer insertUpdate(List<User> users) {
        //新增、导入批量用户；和用户自主注册功能区分开
        //此处只需要提供用户名，其它信息都可以不提供。方便学校或企业批量导入员工；并生成默认密码由个体户自己完善信息
        if (CollectionUtils.isEmpty(users)) {
            throw new BusinessException("用户(集)信息有误！请核实");
        }
        Integer total = 0;
        for (User user : users) {
            if (StringUtils.isEmpty(user.getUserName())) {
                continue;
            }

            total++;
            if (user.getId() != null) {
                user.setPassword(StringUtils.EMPTY);
                commonUserRepository.update(user);
            } else {
                user.setId(System.currentTimeMillis());
                user.setPassword("$2a$10$BMDlpC196w9.IOVcrY1d/OkTWiBHhGLDNLxqT8.s31faVNRnmVjDy");
                commonUserRepository.insert(user);
            }
        }
        return total;
    }

    @Override
    public Integer deleteBatch(List<Long> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            //禁止不传标识
            throw new BusinessException("拒绝操作");
        }
        return commonUserRepository.deleteBatch(ids);
    }
}
