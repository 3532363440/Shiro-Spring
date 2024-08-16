package com.example.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserLogin {
    @RequestMapping(value="/shiro-login",method= RequestMethod.POST)
    public String login(@RequestParam("username") String username,@RequestParam("password") String password){

        try {
            //得到用户
            Subject subject = SecurityUtils.getSubject();
            //进行认证 用户名和密码
            UsernamePasswordToken token = new UsernamePasswordToken(username,password);

            //执行认证操作
            subject.login(token);

        }catch (AuthenticationException e){
            /**
             * 这里要用 AuthenticationException 这个异常输出
             * 要是用 Exception 的话 会知道具体的错误信息，登录失败的时候不能出现具体什么错*/

            System.out.println("登录失败：" + e.getMessage());
            return "/shiro-login"; //要是登录失败就继续跳回登录页面
        }

        return "/shiro-success"; //登录成功页面
    }
}
