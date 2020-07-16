package com.wangweiju.controller;

import com.wangweiju.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class WelcomeController {
    @RequestMapping("/welcome")
    public String queryUserList(Model model){
        return "/welcome.html";
    }
}
