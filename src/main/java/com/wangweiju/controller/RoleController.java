package com.wangweiju.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wangweiju.mapper.RoleMapper;
import com.wangweiju.pojo.Laboratory;
import com.wangweiju.pojo.Role;
import com.wangweiju.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class RoleController {
    @Autowired
    private RoleMapper roleMapper;

    //查询所有角色
    @RequestMapping("/roles")
    public String queryRoleList(Model model,
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
            List<Role> roleList = roleMapper.queryRoleList();
            System.out.println("分页数据：" + roleList);
            //3.使用PageInfo包装查询后的结果,5是连续显示的条数,结果list类型是Page<E>
            PageInfo<Role> pageInfo = new PageInfo<Role>(roleList, pageSize);
            //4.使用model/map/modelandview等带回前端
            model.addAttribute("pageInfo", pageInfo);
            model.addAttribute("roles",roleList);
        } finally {
            PageHelper.clearPage(); //清理 ThreadLocal 存储的分页参数,保证线程安全
        }
        return "/role/rolelist";
    }

    //通过权限等级查询角色
    @RequestMapping("/queryRoleListBylevel")
    public String queryRoleListBylevel(Model model,
                                       @RequestParam("level")int  level,
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
            List<Role> queryRoleListBylevelList = roleMapper.queryRoleListBylevel(level);
            System.out.println("按照权限等级查询为："+queryRoleListBylevelList);
            System.out.println("分页数据：" + queryRoleListBylevelList);
            //3.使用PageInfo包装查询后的结果,5是连续显示的条数,结果list类型是Page<E>
            PageInfo<Role> pageInfo = new PageInfo<Role>(queryRoleListBylevelList, pageSize);
            //4.使用model/map/modelandview等带回前端
            model.addAttribute("pageInfo", pageInfo);
            model.addAttribute("roles",queryRoleListBylevelList);
        } finally {
            PageHelper.clearPage(); //清理 ThreadLocal 存储的分页参数,保证线程安全
        }
        return "/role/rolelist";
    }

    //添加角色
    @GetMapping("/addrole")
    public String toAddpage(Model model){
        return "/role/roleadd";
    }
    @PostMapping("/addrole")
    public String addRole(Role role){
        roleMapper.addRole(role);
        return "redirect:/roles";
    }
    //删除角色
    @GetMapping("/delerole/{id}")
    public String deleteRole(@PathVariable("id")Integer id){
        roleMapper.deleteRole(id);
        return "redirect:/roles";
    }
    //更新一条数据
    //去到员工的修改页面
    @GetMapping("/toroleupdate/{id}")
    public String toUpdateRole(@PathVariable("id")int id,Model model){
        //查出原来的数据
        Role role = roleMapper.queryRoleById(id);
        model.addAttribute("role",role);
        return "/role/roleupdate";
    }
    @PostMapping("/updateRole")
    public String UpdateRole(Role role) {
        roleMapper.updateRole(role);
        return "redirect:/roles";
    }

}
