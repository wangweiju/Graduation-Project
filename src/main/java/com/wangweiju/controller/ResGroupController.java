package com.wangweiju.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wangweiju.mapper.ResGroupMapper;
import com.wangweiju.pojo.ResGroup;
import com.wangweiju.pojo.Role;
import com.wangweiju.pojo.User;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class ResGroupController {
   @Autowired
   private ResGroupMapper resGroupMapper;
    //查询所有信息
    @RequestMapping("/resGroups")
    public  String queryResGroupList(Model model,
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
            List<ResGroup> resGroupList = resGroupMapper.queryResGrouplist();
            System.out.println("分页数据：" + resGroupList);
            //3.使用PageInfo包装查询后的结果,5是连续显示的条数,结果list类型是Page<E>
            PageInfo<ResGroup> pageInfo = new PageInfo<ResGroup>(resGroupList, pageSize);
            //4.使用model/map/modelandview等带回前端
            model.addAttribute("pageInfo", pageInfo);
            model.addAttribute("resGroups",resGroupList);
        } finally {
            PageHelper.clearPage(); //清理 ThreadLocal 存储的分页参数,保证线程安全
        }
        return "/group/grouplist";
    }
    //申请成立课题组(添加项目组)
    @GetMapping("/addgroup")
    public String toAddpage(Model model){
        return "/group/groupadd";
    }

    //展示只有申请人是自己的课题组
    @RequestMapping("/myGroupsOne")
    public  String queryResGroupListByName(Model model,
                                     @RequestParam(required = false,defaultValue="1",value="pageNum")Integer pageNum,
                                     @RequestParam(defaultValue="5",value="pageSize")Integer pageSize){

        User user = (User) SecurityUtils.getSubject().getPrincipal(); // 获取当前登录用户
        String leader = user.getName();
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
            List<ResGroup> queryResGroupListByNameList = resGroupMapper.queryResGrouplistByName(leader);
            System.out.println("分页数据：" + queryResGroupListByNameList);
            //3.使用PageInfo包装查询后的结果,5是连续显示的条数,结果list类型是Page<E>
            PageInfo<ResGroup> pageInfo = new PageInfo<ResGroup>(queryResGroupListByNameList, pageSize);
            //4.使用model/map/modelandview等带回前端
            model.addAttribute("pageInfo", pageInfo);
            model.addAttribute("myGroupsOne",queryResGroupListByNameList);
        } finally {
            PageHelper.clearPage(); //清理 ThreadLocal 存储的分页参数,保证线程安全
        }
        return "/group/Mygroup01";
    }
    //课题组成员管理
    //去到成员管理页面
    @GetMapping("/tomemberUpdate/{id}")
    public String tomemberUpdate(@PathVariable("id")int id,Model model){
        //查出原来的数据
        ResGroup resGroup = resGroupMapper.queryResGroupById(id);
        model.addAttribute("resGroup",resGroup);
        return "/group/memberUpdate";
    }
    @PostMapping("/memberUpdate")
    public  String memberUpdate(ResGroup resGroup){
    resGroupMapper.updateResGroup(resGroup);
        return "redirect:/myGroupsOne";
    }
    //根绝课题组名查询课题组
    @RequestMapping("/queryResGroupListByTopic")
    public String queryResGroupListByTopic(Model model,
                                           @RequestParam("topic")String topic,
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
            List<ResGroup> queryResGroupListByTopicList = resGroupMapper.queryResGrouplistBytopic(topic);
            System.out.println("分页数据：" + queryResGroupListByTopicList);
            //3.使用PageInfo包装查询后的结果,5是连续显示的条数,结果list类型是Page<E>
            PageInfo<ResGroup> pageInfo = new PageInfo<ResGroup>(queryResGroupListByTopicList, pageSize);
            //4.使用model/map/modelandview等带回前端
            model.addAttribute("pageInfo", pageInfo);
            model.addAttribute("resGroups",queryResGroupListByTopicList);
        } finally {
            PageHelper.clearPage(); //清理 ThreadLocal 存储的分页参数,保证线程安全
        }
        return "/group/grouplist";
    }
    //展示自己所在的课题组课题组、

    @RequestMapping("/myGroupsTwo")
    public  String queryResGroupListByMember(Model model,
                                           @RequestParam(required = false,defaultValue="1",value="pageNum")Integer pageNum,
                                           @RequestParam(defaultValue="5",value="pageSize")Integer pageSize){

        User user = (User) SecurityUtils.getSubject().getPrincipal(); // 获取当前登录用户
        String member = user.getName();
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
            List<ResGroup> queryResGroupListByMemberList = resGroupMapper.queryResGroupListByMember(member);
            System.out.println("分页数据：" + queryResGroupListByMemberList);
            //3.使用PageInfo包装查询后的结果,5是连续显示的条数,结果list类型是Page<E>
            PageInfo<ResGroup> pageInfo = new PageInfo<ResGroup>(queryResGroupListByMemberList, pageSize);
            //4.使用model/map/modelandview等带回前端
            model.addAttribute("pageInfo", pageInfo);
            model.addAttribute("myGroupsTwo",queryResGroupListByMemberList);
        } finally {
            PageHelper.clearPage(); //清理 ThreadLocal 存储的分页参数,保证线程安全
        }
        return "/group/Mygroup02";
    }
}
