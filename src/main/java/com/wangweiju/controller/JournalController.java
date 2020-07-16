package com.wangweiju.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wangweiju.mapper.JournalMapper;
import com.wangweiju.pojo.Journal;
import com.wangweiju.pojo.Project;
import com.wangweiju.pojo.User;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class JournalController {
    @Autowired
    private JournalMapper journalMapper;

    //显示出推荐数据(找出和我专业相同的期刊)
    @RequestMapping("/journal")
    public String queryJournalBySubjectList(Model model, @RequestParam(required = false,defaultValue="1",value="pageNum")Integer pageNum,
                                            @RequestParam(defaultValue="5",value="pageSize")Integer pageSize){

        //获取登陆者专业
        User user = (User) SecurityUtils.getSubject().getPrincipal(); // 获取当前登录用户
        String subject = user.getSubject();
        System.out.println("学科为："+subject);
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
            List<Journal> journalList = journalMapper.queryJournalBySubjectList(subject);
            System.out.println("分页数据：" + journalList);
            //3.使用PageInfo包装查询后的结果,5是连续显示的条数,结果list类型是Page<E>
            PageInfo<Journal> pageInfo = new PageInfo<Journal>(journalList, pageSize);
            //4.使用model/map/modelandview等带回前端
            model.addAttribute("pageInfo", pageInfo);
            model.addAttribute("journals",journalList);
        } finally {
            PageHelper.clearPage(); //清理 ThreadLocal 存储的分页参数,保证线程安全
        }
        return "/journal/journallist";
    }
    @RequestMapping("/journals")
    public String queryJournalList(Model model, @RequestParam(required = false,defaultValue="1",value="pageNum")Integer pageNum,
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
            List<Journal> journalList = journalMapper.queryJournalList();
            System.out.println("分页数据：" + journalList);
            //3.使用PageInfo包装查询后的结果,5是连续显示的条数,结果list类型是Page<E>
            PageInfo<Journal> pageInfo = new PageInfo<Journal>(journalList, pageSize);
            //4.使用model/map/modelandview等带回前端
            model.addAttribute("pageInfo", pageInfo);
            model.addAttribute("journals",journalList);
        } finally {
            PageHelper.clearPage(); //清理 ThreadLocal 存储的分页参数,保证线程安全
        }
        return "/journal/journallist";
    }
}
