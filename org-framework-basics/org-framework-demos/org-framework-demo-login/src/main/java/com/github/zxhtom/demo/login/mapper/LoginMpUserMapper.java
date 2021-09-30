package com.github.zxhtom.demo.login.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.zxhtom.demo.login.model.User;
import com.github.zxhtom.demo.login.model.UserMp;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.demo.login.mapper
 * @date 2021/9/28 17:27
 */
@Repository
public interface LoginMpUserMapper extends BaseMapper<UserMp> {
}
