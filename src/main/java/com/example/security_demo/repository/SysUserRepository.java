package com.example.security_demo.repository;

import com.example.security_demo.pojo.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SysUserRepository extends JpaRepository<SysUser,Integer> {

    SysUser findByName(String name);

}
