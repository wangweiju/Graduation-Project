package com.wangweiju.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wangweiju.mapper.SubjectMapper;
import com.wangweiju.mapper.UserMapper;
import com.wangweiju.mapper.UserRoleMapper;
import com.wangweiju.pojo.*;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@Controller
public class UserController {
      @Autowired
      private UserMapper userMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired
    private SubjectMapper subjectMapper;
    //查询所有员工信息
    @RequestMapping("/userRoles")
    public String queryUserRoleList(Model model,
                                    @RequestParam(required = false,defaultValue="1",value="pageNum")Integer pageNum,
                                    @RequestParam(defaultValue="5",value="pageSize")Integer pageSize){

        //为了程序的严谨性，判断非空：
        if (pageNum == null) {
            pageNum = 1;   //设置默认当前页
        }
        if (pageNum <= 0) {
            pageNum = 1;
        }
        if (pageSize == null) {
            pageSize = 5;    //设置默认每页显示的数据数
        }
        System.out.println("当前页是：" + pageNum + "显示条数是：" + pageSize);

        //1.引入分页插件,pageNum是第几页，pageSize是每页显示多少条,默认查询总数count
        PageHelper.startPage(pageNum, pageSize);
        //2.紧跟的查询就是一个分页查询-必须紧跟.后面的其他查询不会被分页，除非再次调用PageHelper.startPage
        try {
            List<UserRole> userRoleListList = userRoleMapper.queryUserRoleList();
            System.out.println("分页数据：" + userRoleListList);
            //3.使用PageInfo包装查询后的结果,5是连续显示的条数,结果list类型是Page<E>
            PageInfo<UserRole> pageInfo = new PageInfo<UserRole>(userRoleListList, pageSize);
            //4.使用model/map/modelandview等带回前端
            model.addAttribute("pageInfo", pageInfo);
            model.addAttribute("userRoles",userRoleListList);
        } finally {
            PageHelper.clearPage(); //清理 ThreadLocal 存储的分页参数,保证线程安全
        }
        return "/user/userlist";
    }
    //通过用户角色查询用户列表
     @RequestMapping("/queryUserListByRole")
     public String queryUserListByRole(Model model,
                                       @RequestParam("perms")String  perms){
             List<UserRole> queryUserListByRoleList = userRoleMapper.queryUserListByRole(perms);
             model.addAttribute("queryUserListByRole",queryUserListByRoleList);
        return "/user/userlistBylevel";
     }
     //通过姓名查询用户
    @RequestMapping("/queryUserListByName")
    public String queryUserListByName(Model model,
                                      @RequestParam("name")String  name,
                                      @RequestParam(required = false,defaultValue="1",value="pageNum")Integer pageNum,
                                      @RequestParam(defaultValue="5",value="pageSize")Integer pageSize){

        //为了程序的严谨性，判断非空：
        if (pageNum == null) {
            pageNum = 1;   //设置默认当前页
        }
        if (pageNum <= 0) {
            pageNum = 1;
        }
        if (pageSize == null) {
            pageSize = 5;    //设置默认每页显示的数据数
        }
        System.out.println("当前页是：" + pageNum + "显示条数是：" + pageSize);

        //1.引入分页插件,pageNum是第几页，pageSize是每页显示多少条,默认查询总数count
        PageHelper.startPage(pageNum, pageSize);
        //2.紧跟的查询就是一个分页查询-必须紧跟.后面的其他查询不会被分页，除非再次调用PageHelper.startPage
        try {
            List<UserRole> queryUserListByNameList = userRoleMapper.queryUserListByName(name);
            System.out.println("分页数据：" + queryUserListByNameList);
            //3.使用PageInfo包装查询后的结果,5是连续显示的条数,结果list类型是Page<E>
            PageInfo<UserRole> pageInfo = new PageInfo<UserRole>(queryUserListByNameList, pageSize);
            //4.使用model/map/modelandview等带回前端
            model.addAttribute("pageInfo", pageInfo);
            model.addAttribute("userRoles",queryUserListByNameList);
        } finally {
            PageHelper.clearPage(); //清理 ThreadLocal 存储的分页参数,保证线程安全
        }
        return "/user/userlist";

    }

    //添加用户信息
    @GetMapping("/adduser")
    public String toAddpage(Model model){
        List<Subject> subjects = subjectMapper.querySubjectList();
        model.addAttribute("subjects",subjects);
        return "/user/useradd";
    }
    @PostMapping("/adduser")
    public String addUser(Users users){
         userMapper.addUser(users);
        return "redirect:/userRoles";
    }

    //删除一条用户信息
    @GetMapping("/deleuser/{id}")
    public String deleteUser(@PathVariable("id")Integer id){
        userMapper.deleteUser(id);
        return "redirect:/userRoles";
    }
    //更新一条数据
    //去到员工的修改页面
    @GetMapping("/touserupdate/{id}")
    public String toUpdateUser(@PathVariable("id")int id,Model model){
        //查出原来的数据
        List<Subject> subjects = subjectMapper.querySubjectList();
        model.addAttribute("subjects",subjects);
        User user = userMapper.queryUserById(id);
        model.addAttribute("user",user);
        return "/user/userupdate";
    }
    @PostMapping("/updaeUser")
    public String UpdateUser(User user) {
       userMapper.updateUser(user);
        return "redirect:/userRoles";
    }
    @RequestMapping("/userinfo")
    public String Userinfo(Model model){
        User user = (User) SecurityUtils.getSubject().getPrincipal(); // 获取当前登录用户
        String name = user.getName();
        User userInfo = userMapper.queryUserByName(name);
        model.addAttribute("userinfo",userInfo);
        List<Subject> subjects = subjectMapper.querySubjectList();
        model.addAttribute("subjects",subjects);
        return "/user/userinfo";
    }
}
