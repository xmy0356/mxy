package com.example.security_demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @GetMapping("/toLogin")
    public String toLogin(){
        return "login";
    }

    @PostMapping("/index")
    public String index(){
        return "index";
    }


}
