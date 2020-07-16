package com.wangweiju.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wangweiju.mapper.ApplipatentMapper;
import com.wangweiju.pojo.*;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ApplipatentController {
    @Autowired
    private ApplipatentMapper applipatentMapper;

    @RequestMapping("/patents")
    public String AppliPatent(Model model){
        //只能查看自己申请的专利
        User user = (User) SecurityUtils.getSubject().getPrincipal(); // 获取当前登录用户
        String applicant = user.getName();
        List<Applipatent> applipatentByAuthorList = applipatentMapper.queryApplipatentByApplicant(applicant);
        model.addAttribute("patents",applipatentByAuthorList);
        return "/patent/patent";
    }
    //添加申请专利录入数据
    @RequestMapping("/addApplipatent")
    public String addApplipatent(Applipatents applipatents){
        applipatentMapper.addApplipatent(applipatents);
        return "redirect:/patents";
    }
    //专利录入审核列表
    @RequestMapping("/applipatents")
    public String applipatents(Model model){
        List<Applipatent> applipatentList = applipatentMapper.queryApplipatentList();
        model.addAttribute("applipatents",applipatentList);
        return "/patent/applipatentlist";
    }
    //拒绝审核
    @RequestMapping("/rejectApplipatent/{id}")
    public String rejectApplipaper(@PathVariable("id")int id){
        Applipatent applipatent = applipatentMapper.queryApplipatentById(id);
        applipatent.setStatus("未通过");
        applipatentMapper.updateApplipatent(applipatent);
        return "redirect:/applipatents";
    }
    //通过审核
    @RequestMapping("/agreeApplipatent/{id}")
    public String agreeApplipaper(@PathVariable("id")int id){
        Applipatent applipatent = applipatentMapper.queryApplipatentById(id);
        applipatent.setStatus("已通过");
        applipatentMapper.updateApplipatent(applipatent);
        //同意后将数据插入到论文库表中
        applipatentMapper.addPatent(applipatent);
        return "redirect:/applipatents";
    }
    //根据审核状态查询审核列表
    @RequestMapping("/queryApplipatentListByStatus")
    public String queryApplipatentListByStatus(Model model,
                                               @RequestParam("status")String status){
        List<Applipatent> queryApplipatentListByStatusList = applipatentMapper.queryApplipatentByStastus(status);
        model.addAttribute("applipatents",queryApplipatentListByStatusList);
        return "/patent/applipatentlist";
    }

    //专利库列表
    @RequestMapping("/patent")
    public String patent(Model model,
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
            List<Patent> patentList = applipatentMapper.queryPatentList();
            System.out.println("分页数据：" + patentList);
            //3.使用PageInfo包装查询后的结果,5是连续显示的条数,结果list类型是Page<E>
            PageInfo<Patent> pageInfo = new PageInfo<Patent>(patentList, pageSize);
            //4.使用model/map/modelandview等带回前端
            model.addAttribute("pageInfo", pageInfo);
            model.addAttribute("patentlist",patentList);
        } finally {
            PageHelper.clearPage(); //清理 ThreadLocal 存储的分页参数,保证线程安全
        }
        return "/patent/patentlist";
    }
    //根据申请者在专利库中查询专利
    @RequestMapping("/queryPatentByApplicant")
    public String queryPatentByApplicant(Model model,
                                         @RequestParam("applicant")String applicant){
        List<Patent> queryPatentByApplicantList = applipatentMapper.queryPatentByApplicant(applicant);
        model.addAttribute("patentlist",queryPatentByApplicantList);
        return "/patent/patentlist";
    }
    //修改信息
    //去到专利修改页面
    @GetMapping("/toupdateApplipatent/{id}")
    public String toupdate(@PathVariable("id")int id,Model model){
        //查出数据
        Applipatent applipatent = applipatentMapper.queryApplipatentById(id);
        System.out.println("查出的数据为："+applipatent);
        model.addAttribute("applipatent",applipatent);
        return "/patent/patentInfo";
    }
    @PostMapping("/updateApplipatent")
    public String updateApplipatent(Applipatent applipatent){
        applipatentMapper.updateApplipatent(applipatent);
        return "redirect:/patents";
    }
}
