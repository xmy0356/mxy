package com.example.security_demo.config;

import com.example.security_demo.util.MyPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * 定义一个关于权限认证的配置类
 */
//@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private MyPasswordEncoder passwordEncoder;

    /**
     * 配置认证相关的内容
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        String s = passwordEncoder.encode("123456");
        //基于内存模式配置认证账号与密码以及权限
        auth.inMemoryAuthentication()
                .withUser("admin")
                .password(s)
                .roles("ADMIN","USER")
                .and()
                .withUser("jack")
                .password(s)
                .roles("USER")
                ;
    }


}
