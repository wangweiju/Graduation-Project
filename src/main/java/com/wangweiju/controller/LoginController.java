package com.wangweiju.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @RequestMapping("/user/login")
    public  String login(String username,String password,Model model){
        //获取当前用户
        Subject subject = SecurityUtils.getSubject();

        //封装用户的登录数据
        UsernamePasswordToken token =  new UsernamePasswordToken(username,password);
        System.out.println(username);

        System.out.println(password);
        try {
            subject.login(token);//执行登录方法，如果没有异常就ok
            System.out.println("去向main页面");
            return "redirect:/main.html";
        }catch(UnknownAccountException e){//用户名不存在
            model.addAttribute("msg","用户名错误");
            return "index";
        }catch(IncorrectCredentialsException e){//密码不存在
            model.addAttribute("msg","密码错误");
            return "index";
        }
    }
    //注销
    @RequestMapping("/logout")
    public String logout(HttpSession session){
        System.out.println("输出的session为："+session);
        if (session.isNew()){
            session.invalidate();
        }
        return "redirect:/index.html";
    }
}
