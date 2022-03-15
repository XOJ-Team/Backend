package com.xoj.backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/***
 * @Author yezhen
 * @Date 21:33 2022/3/14
 ***/
public class UserDetailsServiceImpl  implements UserDetailsService {

    @Autowired
    LoginServiceImpl loginService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //需要构造出 org.springframework.security.core.userdetails.User 对象并返回
        if (username == null || "".equals(username)) {
            throw new RuntimeException("user can't be nulll");
        }
        //根据用户名查询用户
        com.xoj.backend.entity.User user = loginService.getUser(username);
        if (user == null) {
            throw new RuntimeException("user not exist");
        }
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(user.getAuthority().toString());
        grantedAuthorities.add(grantedAuthority);
        return new User(user.getMail(), user.getPassword(), grantedAuthorities);

    }
}