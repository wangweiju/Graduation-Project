package com.wangweiju.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wangweiju.mapper.AppligroMapper;
import com.wangweiju.pojo.*;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class AppligroController {
    @Autowired
    private AppligroMapper appligroMapper;
    //查询所有成立课题组的申请
    @RequestMapping("/appligros")
    public String queryAppligroList(Model model,
                                    @RequestParam(required = false,defaultValue="1",value="pageNum")Integer pageNum,
                                    @RequestParam(defaultValue="5",value="pageSize")Integer pageSize) {
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
            List<Appligro> queryAppligroList = appligroMapper.queryAppligroList();
            System.out.println("分页数据：" + queryAppligroList);
            //3.使用PageInfo包装查询后的结果,5是连续显示的条数,结果list类型是Page<E>
            PageInfo<Appligro> pageInfo = new PageInfo<Appligro>(queryAppligroList, pageSize);
            //4.使用model/map/modelandview等带回前端
            model.addAttribute("pageInfo", pageInfo);
            model.addAttribute("appligros",queryAppligroList);
        } finally {
            PageHelper.clearPage(); //清理 ThreadLocal 存储的分页参数,保证线程安全
        }
        return "/applila/appligrolist";
    }
    //插入一条申请数据至课题组申请表
    @PostMapping("/addgroup")
    public String addgroup(Appligros appligros){
        appligroMapper.addApppligro(appligros);
        return "redirect:/appligros";
    }
    //拒绝申请
    @RequestMapping("/rejectAppligro/{id}")
    public String rejectAppligro(@PathVariable("id")int id){
        Appligro appligro = appligroMapper.queryAppligroById(id);
        appligro.setStatus("未通过");
        System.out.println("拒绝后的数据表："+appligro);
        appligroMapper.rejectAppligro(appligro);
        return "redirect:/appligros";
    }
    //同意申请
    @RequestMapping("/agreeAppligro/{id}")
    public String agreeAppligro(@PathVariable("id")int id){
        //更改申请表的状态
        Appligro appligro = appligroMapper.queryAppligroById(id);
        appligro.setStatus("已通过");
        appligroMapper.rejectAppligro(appligro);
        System.out.println("同意后的数据表："+appligro);
        //将数据添加到课题组列表中
        appligroMapper.addgroup(appligro);
        return "redirect:/appligros";
    }
    //通过申请状态查询申请列表
    @RequestMapping("/queryAppligroListByStatus")
    public String queryAppligroListByStatus(Model model,
                                            @RequestParam("status")String status){
            List<Appligro> queryAppligroListByStatusList = appligroMapper.queryAppligroByStatus(status);
            System.out.println("分页数据：" + queryAppligroListByStatusList);
            model.addAttribute("queryAppligroListByStatus",queryAppligroListByStatusList);
        return "/applila/appligrolistByStatus";
    }


    //查看自己的课题组成立申请
    @RequestMapping("/Myappligro")
    public String queryAppligroByName(Model model,
                                      @RequestParam(required = false,defaultValue="1",value="pageNum")Integer pageNum,
                                      @RequestParam(defaultValue="5",value="pageSize")Integer pageSize){
        User user = (User) SecurityUtils.getSubject().getPrincipal(); // 获取当前登录用户
        String name = user.getName();
        System.out.println("User为："+user);
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
            List<Appligro> queryAppligroByNameList = appligroMapper.queryAppligroByName(name);
            System.out.println("分页数据：" + queryAppligroByNameList);
            //3.使用PageInfo包装查询后的结果,5是连续显示的条数,结果list类型是Page<E>
            PageInfo<Appligro> pageInfo = new PageInfo<Appligro>(queryAppligroByNameList, pageSize);
            //4.使用model/map/modelandview等带回前端
            model.addAttribute("pageInfo", pageInfo);
            model.addAttribute("Myappligro",queryAppligroByNameList);
        } finally {
            PageHelper.clearPage(); //清理 ThreadLocal 存储的分页参数,保证线程安全
        }
        return "/applila/Myappligro";
    }
}