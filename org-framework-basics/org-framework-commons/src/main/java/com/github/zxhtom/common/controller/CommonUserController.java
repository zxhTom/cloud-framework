package com.github.zxhtom.common.controller;

import com.github.zxhtom.common.service.CommonUserService;
import com.github.zxhtom.core.annotaion.msg.Unite;
import com.github.zxhtom.core.datasouce.PagedResult;
import com.github.zxhtom.login.core.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.common.controller
 * @date 2021/10/22 11:09
 */
@RestController
@RequestMapping(value = "/framework_common/sysUser")
@Unite
public class CommonUserController {

    @Autowired
    CommonUserService commonUserService;

    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public PagedResult<User> selectSysUsersByPK(@PathVariable @NotNull Long userId,
                                          @RequestParam(required = false) Integer pageNumber,
                                          @RequestParam(required = false) Integer pageSize) {
        return commonUserService.selectSysUsersByPK(userId, pageNumber,  pageSize);
    }

    @RequestMapping(value = "/insertUpdate", method = RequestMethod.POST)
    public Integer insertUpdate(@RequestBody List<User> users) {
        return commonUserService.insertUpdate(users);
    }

    @RequestMapping(value = "/deleteBatch", method = RequestMethod.DELETE)
    public Integer deleteBatch(@RequestBody List<Long> ids) {
        return commonUserService.deleteBatch(ids);
    }

}
