package com.example.security_demo.util;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.DigestUtils;

/**
 * 自定义一个密码编码器，实现密码的加密以及原密码和加密密码的比对
 */
//@Component
public class MyPasswordEncoder implements PasswordEncoder {
    //对原始密码进行编码   此处可以自定义编码
    @Override
    public String encode(CharSequence rawPassword) {
        //md5加密
        return DigestUtils.md5DigestAsHex(rawPassword.toString().getBytes());
    }

    //对原始密码以及编码后的密码进行比对   根据编码对应进行解码比较密码
    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return encodedPassword.equals(DigestUtils.md5DigestAsHex(rawPassword.toString().getBytes()));
    }
}
