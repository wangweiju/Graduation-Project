package com.wangweiju.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wangweiju.mapper.ApplipaperMapper;
import com.wangweiju.mapper.SubjectMapper;
import com.wangweiju.pojo.*;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ApplipaperController {
    @Autowired
    private ApplipaperMapper applipaperMapper;
    @Autowired
    private SubjectMapper subjectMapper;
    //查询所有申请的录入的论文
    @RequestMapping("/papers")
  public String AppliPper(Model model){
       //只能查看自己录入的论文
        User user = (User) SecurityUtils.getSubject().getPrincipal(); // 获取当前登录用户
        String author = user.getName();
        List<Subject> subjects = subjectMapper.querySubjectList();
        List<Applipaper> applipaperByAuthorList = applipaperMapper.queryApplipaperByAuthor(author);
        model.addAttribute("subjects",subjects);
        model.addAttribute("papers",applipaperByAuthorList);
        return "/paper/paper";
  }
  //添加申请论文录入数据
  @RequestMapping("/addApplipaper")
    public String addApplipaper(Applipapers applipapers){
        applipaperMapper.addApplipaper(applipapers);
       return "redirect:/papers";
  }
  //论文录入审核列表
  @RequestMapping("/applipapers")
    public String applipapers(Model model){
        List<Applipaper> applipaperList = applipaperMapper.queryApplipaperList();
        model.addAttribute("applipapers",applipaperList);
      return "/paper/applipaperlist";
  }
//拒绝审核
    @RequestMapping("/rejectApplipaper/{id}")
    public String rejectApplipaper(@PathVariable("id")int id){
        Applipaper applipaper = applipaperMapper.queryApplipaperById(id);
        applipaper.setStatus("未通过");
        applipaperMapper.updateApplipaper(applipaper);
        return "redirect:/applipapers";
    }
    //通过审核
    @RequestMapping("/agreeApplipaper/{id}")
    public String agreeApplipaper(@PathVariable("id")int id){
        Applipaper applipaper = applipaperMapper.queryApplipaperById(id);
        applipaper.setStatus("已通过");
        applipaperMapper.updateApplipaper(applipaper);
        //同意后将数据插入到论文库表中
        applipaperMapper.addPaper(applipaper);
        return "redirect:/applipapers";
    }
  //根据审核状态查询审核列表
    @RequestMapping("/queryApplipaperListByStatus")
    public String queryApplipaperListByStatus(Model model,
                                              @RequestParam("status")String status){
       List<Applipaper> queryApplipaperListByStatusList = applipaperMapper.queryApplipaperByStastus(status);
       model.addAttribute("applipapers",queryApplipaperListByStatusList);
        return "/paper/applipaperlist";
    }

  //论文库列表
    @RequestMapping("/paper")
    public String paper(Model model,
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
            List<Paper> paperList = applipaperMapper.queryPaperList();
            System.out.println("分页数据：" + paperList);
            //3.使用PageInfo包装查询后的结果,5是连续显示的条数,结果list类型是Page<E>
            PageInfo<Paper> pageInfo = new PageInfo<Paper>(paperList, pageSize);
            //4.使用model/map/modelandview等带回前端
            model.addAttribute("pageInfo", pageInfo);
            model.addAttribute("paperlist",paperList);
        } finally {
            PageHelper.clearPage(); //清理 ThreadLocal 存储的分页参数,保证线程安全
        }
        return "/paper/paperlist";
    }
    //根据作者在论文库中查询论文
    @RequestMapping("/queryPaperByAuthor")
    public String queryPaperByAuthor(Model model,
                                     @RequestParam("author")String author){
        List<Paper> queryPaperByAuthorList = applipaperMapper.queryPaperByAuthor(author);
        model.addAttribute("paperlist",queryPaperByAuthorList);
        return "/paper/paperlist";
    }
    //修改信息
    //去到论文修改页面
    @GetMapping("/toupdateApplipaper/{id}")
    public String toupdate(@PathVariable("id")int id,Model model){
        //查出数据
      Applipaper applipaper =applipaperMapper.queryApplipaperById(id);
        System.out.println("查出的数据为："+applipaper);
      model.addAttribute("applipaper",applipaper);
        List<Subject> subjects = subjectMapper.querySubjectList();
        model.addAttribute("subjects",subjects);
      return "/paper/paperInfo";
    }
    @PostMapping("/updateApplipaper")
    public String updateApplipaper(Applipaper applipaper){
        applipaperMapper.updateApplipaper(applipaper);
        return "redirect:/papers";
    }
}
