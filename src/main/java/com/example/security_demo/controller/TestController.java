package com.example.security_demo.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/")
    public String index(){
        return "ok!!!!!!";
    }

    /**
     * @Secured: 标注在web资源上，表示访问当前资源的用户必须具有某种角色
     */
    @GetMapping("/admin")
//    @Secured("ROLE_ADMIN")
    public String admin(){
        return "ADMIN!!!!!!";
    }

    @GetMapping("/user")
//    @Secured({"ROLE_ADMIN","ROLE_USER"})
    public String userTest(){
        return "USER!!!!!!";
    }

    @GetMapping("/normal")
//    @Secured("ROLE_USER")
    public String normal(){
        return "normal........";
    }

    @GetMapping("/vip")
//    @Secured("ROLE_ADMIN")
    public String vip(){
        return "vip........";
    }

}
