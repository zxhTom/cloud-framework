package com.github.zxhtom.demo.login.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.zxhtom.demo.login.abCanMulti.AbStractSoutBean;
import com.github.zxhtom.demo.login.mapper.LoginMpUserMapper;
import com.github.zxhtom.demo.login.mapper.LoginUserMapper;
import com.github.zxhtom.demo.login.model.UserMp;
import com.github.zxhtom.login.core.mapper.UserMapper;
import com.github.zxhtom.login.core.model.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.demo.login.controller
 * @date 2021/9/15 9:52
 */
@RestController
@RequestMapping(value = "/test")
public class TestController {

    @Autowired
    SqlSessionFactory sqlSessionFactory;
    @Autowired
    UserMapper userMapper;
    @Autowired
    LoginUserMapper loginUserMapper;
    @Autowired
    LoginMpUserMapper loginMpUserMapper;

    @Autowired
    List<AbStractSoutBean> bean;
    @RequestMapping(value = "/mulbean",method = RequestMethod.GET)
    public void mulbean() {
        System.out.println(bean.size());
    }

    @RequestMapping(value = "/twoModel",method = RequestMethod.GET)
    public List<com.github.zxhtom.demo.login.model.User> twoModel() {
        com.github.zxhtom.demo.login.model.User user = new com.github.zxhtom.demo.login.model.User();
        UserMp userMp = new UserMp();
        user.setUserName("zxhtom1231");
        userMp.setUserName("zxhtom1231");
        return loginUserMapper.selectTwoUser(user,userMp);
    }
    @RequestMapping(value = "/hello")
    @PreAuthorize("hasRole('admin')")
    public String hello() {
        //List<User> users = userMapper.selectUserList();
        //System.out.println(users);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper2 = sqlSession.getMapper(UserMapper.class);
        List<User> users = userMapper.selectUserList();
        List<com.github.zxhtom.demo.login.model.User> userls = loginUserMapper.selectUserList();
        List<UserMp> userMps = loginMpUserMapper.selectList(new QueryWrapper<>());
        System.out.println(userMps);
        System.out.println(users);
        System.out.println(userls);
        return "hello zxhtom";
    }

    @RequestMapping(value = "/hello2")
    public String hello2() {
        return "hello zxhtom2";
    }

    @RequestMapping("/user")
    public void userInfo() {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        Collection<? extends GrantedAuthority> authorities =
                authentication.getAuthorities();
        System.out.println("name = " + name);
        System.out.println("authorities = " + authorities);
    }
}
