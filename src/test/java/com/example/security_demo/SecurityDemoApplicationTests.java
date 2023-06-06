package com.example.security_demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class SecurityDemoApplicationTests {

    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @Test
    void contextLoads() {
        String s = passwordEncoder.encode("123456");
        System.out.println("s = " + s);
    }

}
