package com.example.controller;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresGuest;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {
    /**
     * @RequiresAuthentication ：要求用户进行身份认证（登录）后才能访问该类或该方法
     * @RequiresUser ：要求身份认证或者是记住我的功能才能访问
     * @RequiresRoles("校长") ：要求用户具有指定的角色才能访问该类或方法
     * @RequiresPermissions("/aaa") ：要求用户具有指定的权限才能访问该方法或类
     * @RequiresGuest ：游客登录）这个的要求是 在不登录之前就可以访问，要是登录后就不能访问了，前提是要在配置文件中给  /** = authc 注释掉
     * */
    @RequestMapping(value = "/abc")
    public String abc(){
        System.out.println("123");
        return "/custom-permission";
    }
}
