package com.github.zxhtom.common.service;

import com.github.zxhtom.login.core.dto.MenuDto;

import java.util.List;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.common.service
 * @date 2021/10/12 16:24
 */
public interface CommonSystemService {
    /**
    * @author zxhtom
    * @Description 查询菜单数据、
     * 文件夹菜单无跳转链接，系分组概念
     * 实际菜单存在父子结构，父子结构和文件夹并无冲突理念
     * 菜单需要根据模块和角色进行查询，多模块菜单先按照模块排序编号进行排序，内部在按照菜单排序编号进行排序
    * @Date 16:59 2021/10/12
    */
    List<MenuDto> selectMenuListByLoginUser(Long moduleId);

    /**
    * @author zxhtom
    * @Description
    * @Date 19:02 2021/10/18
     * @description
     * 获取用户登录信息，因为不确定下游具体使用的是哪种登录方式，所以这里的用户实体也无法
     * 确认，这里object统一
    */
    Object userInfo();

}
