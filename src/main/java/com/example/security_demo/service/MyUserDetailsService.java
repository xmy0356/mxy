package com.example.security_demo.service;

import com.example.security_demo.pojo.SysRole;
import com.example.security_demo.pojo.SysUser;
import com.example.security_demo.repository.SysUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 实现UserDetailsService
 */
@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private SysUserRepository userRepository;


    /**
     * security默认根据用户名查询用户信息
     * @param s 登录认证的用户名
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        //根据用户名进行查询
        SysUser sysUser = userRepository.findByName(s);
        if (Objects.isNull(sysUser)){
            throw new UsernameNotFoundException("用户不存在!!!!");
        }
        //获取当前用户的角色列表 , 并将其封装
        List<SysRole> roles = sysUser.getRoles();
        List<GrantedAuthority> authorities = new ArrayList<>();
        roles.forEach(sysRole -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + sysRole.getRole()));
        });
        User user = new User(s, sysUser.getPassword(), authorities);
        return user;
    }
}
