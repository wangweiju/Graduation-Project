package com.wangweiju.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wangweiju.mapper.ApplibookMapper;
import com.wangweiju.pojo.*;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ApplibookController {
    @Autowired
    private ApplibookMapper applibookMapper;

    @RequestMapping("/books")
    public String AppliBook(Model model){
        //只能查看自己申请录入的专著
        User user = (User) SecurityUtils.getSubject().getPrincipal(); // 获取当前登录用户
        String author = user.getName();
        List<Applibook> applibookByAuthorList = applibookMapper.queryApplibookByAuthor(author);
        model.addAttribute("books",applibookByAuthorList);
        return "/book/book";
    }
    //添加申请专著录入数据
    @RequestMapping("/addApplibook")
    public String addApplibook(Applibooks applibooks){
        applibookMapper.addApplibook(applibooks);
        return "redirect:/books";
    }
    //专著录入审核列表
    @RequestMapping("/applibooks")
    public String applibooks(Model model){
        List<Applibook> applibookList = applibookMapper.queryApplibookList();
        model.addAttribute("applibooks",applibookList);
        return "/book/applibooklist";
    }
    //拒绝审核
    @RequestMapping("/rejectApplibook/{id}")
    public String rejectApplibook(@PathVariable("id")int id){
        Applibook applibook = applibookMapper.queryApplibookById(id);
        applibook.setStatus("未通过");
        applibookMapper.updateApplibook(applibook);
        return "redirect:/applibooks";
    }
    //通过审核
    @RequestMapping("/agreeApplibook/{id}")
    public String agreeApplibook(@PathVariable("id")int id){
        Applibook applibook = applibookMapper.queryApplibookById(id);
        applibook.setStatus("已通过");
        applibookMapper.updateApplibook(applibook);
        //同意后将数据插入到论文库表中
        applibookMapper.addBook(applibook);
        return "redirect:/applibooks";
    }
    //根据审核状态查询审核列表
    @RequestMapping("/queryApplibookListByStatus")
    public String queryApplibookListByStatus(Model model,
                                               @RequestParam("status")String status){
        List<Applibook> queryApplibookListByStatusList = applibookMapper.queryApplibookByStastus(status);
        model.addAttribute("applibooks",queryApplibookListByStatusList);
        return "/book/applibooklist";
    }

    //专著库列表
    @RequestMapping("/book")
    public String book(Model model,
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
            List<Book> bookList = applibookMapper.queryBookList();
            System.out.println("分页数据：" + bookList);
            //3.使用PageInfo包装查询后的结果,5是连续显示的条数,结果list类型是Page<E>
            PageInfo<Book> pageInfo = new PageInfo<Book>(bookList, pageSize);
            //4.使用model/map/modelandview等带回前端
            model.addAttribute("pageInfo", pageInfo);
            model.addAttribute("booklist",bookList);
        } finally {
            PageHelper.clearPage(); //清理 ThreadLocal 存储的分页参数,保证线程安全
        }
        return "/book/booklist";
    }
    //根据作者在专利库中查询专利
    @RequestMapping("/queryBookByAuthor")
    public String queryBookByAuthor(Model model,
                                         @RequestParam("author")String author){
        List<Book> queryBookByAuthorList = applibookMapper.queryBookByAuthort(author);
        model.addAttribute("booklist",queryBookByAuthorList);
        return "/book/booklist";
    }
    //修改信息
    //去到专著修改页面
    @GetMapping("/toupdateApplibook/{id}")
    public String toupdate(@PathVariable("id")int id,Model model){
        //查出数据
        Applibook applibook = applibookMapper.queryApplibookById(id);
        System.out.println("查出的数据为："+applibook);
        model.addAttribute("applibook",applibook);
        return "/book/bookInfo";
    }
    @PostMapping("/updateApplibook")
    public String updateApplibook(Applibook applibook){
        applibookMapper.updateApplibook(applibook);
        return "redirect:/books";
    }
}
