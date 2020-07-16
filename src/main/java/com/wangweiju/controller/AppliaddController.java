package com.wangweiju.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wangweiju.mapper.AppliaddMapper;
import com.wangweiju.mapper.ResGroupMapper;
import com.wangweiju.pojo.Appliadd;
import com.wangweiju.pojo.Appligro;
import com.wangweiju.pojo.ResGroup;
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
public class AppliaddController {
    @Autowired
    private AppliaddMapper appliaddMapper;
    @Autowired
    private ResGroupMapper resGroupMapper;
    //查询所有的课题组成员加入申请
    @RequestMapping("/appliadds")
    public String queryAppliaddList(Model model,
                                    @RequestParam(required = false,defaultValue="1",value="pageNum")Integer pageNum,
                                    @RequestParam(defaultValue="5",value="pageSize")Integer pageSize){

        User user = (User) SecurityUtils.getSubject().getPrincipal(); // 获取当前登录用户
        String leader = user.getName();
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
            List<Appliadd> queryAppliaddList = appliaddMapper.queryAppliaddList(leader);
            System.out.println("分页数据：" + queryAppliaddList);
            //3.使用PageInfo包装查询后的结果,5是连续显示的条数,结果list类型是Page<E>
            PageInfo<Appliadd> pageInfo = new PageInfo<Appliadd>(queryAppliaddList, pageSize);
            //4.使用model/map/modelandview等带回前端
            model.addAttribute("pageInfo", pageInfo);
            model.addAttribute("appliadds",queryAppliaddList);
        } finally {
            PageHelper.clearPage(); //清理 ThreadLocal 存储的分页参数,保证线程安全
        }
        return "/applila/appliaddlist";
    }
    @RequestMapping("/appliadd/{id}")
    public String insertAppliadd(@PathVariable("id")int id){
        //获取登录用户名
        User user = (User) SecurityUtils.getSubject().getPrincipal(); // 获取当前登录用户
        String name = user.getName();
        //获取要加入的课题组的信息
        ResGroup resGroup = resGroupMapper.queryResGroupById(id);
        appliaddMapper.insertAppliadd(resGroup.getTopic(),resGroup.getLeader(),resGroup.getMember(),name);
        return "redirect:/Myappliadd";
    }
    @RequestMapping("/Myappliadd")
    public String queryAppliaddListByName(Model model,
                                          @RequestParam(required = false,defaultValue="1",value="pageNum")Integer pageNum,
                                          @RequestParam(defaultValue="5",value="pageSize")Integer pageSize){
        // 获取当前登录用户
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        String name = user.getName();
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
            List<Appliadd> queryAppliaddListByNameList = appliaddMapper.queryAppliaddListByName(name);
            System.out.println("分页数据：" + queryAppliaddListByNameList);
            //3.使用PageInfo包装查询后的结果,5是连续显示的条数,结果list类型是Page<E>
            PageInfo<Appliadd> pageInfo = new PageInfo<Appliadd>(queryAppliaddListByNameList, pageSize);
            //4.使用model/map/modelandview等带回前端
            model.addAttribute("pageInfo", pageInfo);
            model.addAttribute("Myappliadd",queryAppliaddListByNameList);
        } finally {
            PageHelper.clearPage(); //清理 ThreadLocal 存储的分页参数,保证线程安全
        }
        return "/applila/Myappliadd";
    }
    //通过statush和leader进行查询（不进行分页）
    @RequestMapping("/queryAppliaddListByStatus")
    public String queryAppliaddListByStatus(Model model,
                                            @RequestParam("status")String status){
        User user = (User) SecurityUtils.getSubject().getPrincipal(); // 获取当前登录用户
        String leader = user.getName();
       List<Appliadd> queryAppliaddListByStatusList = appliaddMapper.queryAppliaddListByStatus(status,leader);
       model.addAttribute("queryAppliaddListByStatus",queryAppliaddListByStatusList);
        return "/applila/appliaddlistByStatus";
    }
    //拒绝申请
    @RequestMapping("/rejectAppliadd/{id}")
    public  String rejectAppliadd(@PathVariable("id")int id){
        Appliadd appliadd = appliaddMapper.queryAppliaddById(id);
        appliadd.setStatus("未通过");
        appliaddMapper.rejectAppliadd(appliadd);
        return "redirect:/appliadds";

    }
    //同意申请
    @RequestMapping("/agreeAppliadd/{id}")
    public String agreeAppliadd(@PathVariable("id")int id){
        Appliadd appliadd = appliaddMapper.queryAppliaddById(id);
        appliadd.setStatus("已通过");
        appliaddMapper.rejectAppliadd(appliadd);
        String memberOne = appliadd.getMember();
        String memberTwo = appliadd.getName();
        String member = memberOne+"，"+memberTwo;
        System.out.println("同意后的课题组成员为："+member);
        String topic = appliadd.getTopic();
        appliaddMapper.agreeAppliadd(member,topic);
        return "redirect:/appliadds";
    }
}
