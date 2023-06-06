package com.example.security_demo.config;

import com.example.security_demo.handler.MyAccessDeniedHandler;
import com.example.security_demo.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class MySecurityConfig extends WebSecurityConfigurerAdapter {
    //往容器中注入一个密码编码器
    @Bean
    public PasswordEncoder myPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private MyAccessDeniedHandler accessDeniedHandler;


    /**
     * 判断认证逻辑
     *
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //通过数据库查询进行登录的认证
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(myPasswordEncoder());
    }

    //针对http资源的权限配置
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //配置登录相关内容
        http.formLogin().loginPage("/toLogin")
                .loginProcessingUrl("/login")
                .successForwardUrl("/index")
        ;
        //http资源的配置
        http.authorizeRequests()
                .antMatchers("/toLogin","/login","/index")
                .permitAll()  //不用认证就可以访问
                .antMatchers("/")
                .authenticated()  //需要认证才能访问
                .antMatchers("/admin","/vip")
                .hasRole("ADMIN") //必须具有相关权限才能访问
                .antMatchers("/user")
                .hasAnyRole("ADMIN","USER")
        ;
        //使用自己的拒绝访问处理器
        http.exceptionHandling()
                .accessDeniedHandler(accessDeniedHandler);
        //配置注销退出
        http.logout().logoutUrl("/logout");
        //remember-me
        http.rememberMe().tokenValiditySeconds(24*60*60);
    }
}
