package com.wangweiju.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wangweiju.mapper.ApplilaMapper;
import com.wangweiju.mapper.LaboratoryMapper;
import com.wangweiju.mapper.ResGroupMapper;
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
public class ApplilaController {
    @Autowired
    private ApplilaMapper applilaMapper;
    @Autowired
    private ResGroupMapper resGroupMapper;
    @Autowired
    private LaboratoryMapper laboratoryMapper;
    //查询所有实验室申请列表
    @RequestMapping("/applilas")
    public String queryApplilaList(Model model,
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
            List<Applila> queryApplilaList = applilaMapper.queryApplilaList();
            System.out.println("分页数据：" + queryApplilaList);
            //3.使用PageInfo包装查询后的结果,5是连续显示的条数,结果list类型是Page<E>
            PageInfo<Applila> pageInfo = new PageInfo<Applila>(queryApplilaList, pageSize);
            //4.使用model/map/modelandview等带回前端
            model.addAttribute("pageInfo", pageInfo);
            model.addAttribute("applilas",queryApplilaList);
        } finally {
            PageHelper.clearPage(); //清理 ThreadLocal 存储的分页参数,保证线程安全
        }
        return "/applila/applilalist";
    }
    //插入一条申请数据到实验室申请表
    @PostMapping("/addapplila")
    public String addapplila(Applilas applilas){
        applilaMapper.addApplila(applilas);
        return "redirect:/Myapplila";
    }
    //拒绝申请
    @RequestMapping("/rejectApplila/{id}")
    public String rejectApplila(@PathVariable("id")int id){
        Applila applila = applilaMapper.queryApplilaById(id);
        applila.setStatus("未通过");
        System.out.println("拒绝后的数据表："+applila);
        applilaMapper.rejectApplila(applila);
        return "redirect:/applilas";
    }
    //通过申请
    @RequestMapping("/sucApplila/{id}/{num}/{topic}")
    public String sucApplila(@PathVariable("id")int id,
                             @PathVariable("num")String num,
                             @PathVariable("topic")String topic){
        //根据课题组名从课题组中查出课题ID
       ResGroup resGroup = resGroupMapper.queryResGrouplistByTopic(topic);
       if(resGroup==null){
           return "/error/Null";
       }else {
              //更新实验室对应的课题组表
              int pro_id = resGroup.getId();
              laboratoryMapper.updateLaboratory(num,pro_id);
              //更新实验室申请表中的申请状态字段
              Applila applila = applilaMapper.queryApplilaById(id);
              applila.setStatus("已通过");
              System.out.println("拒绝后的数据表："+applila);
              applilaMapper.rejectApplila(applila);
              return "redirect:/applilas";
       }
    }
    //通过状态查询申请列表
    @RequestMapping("queryApplilaListByStatus")
    public String queryApplilaListByStatus(Model model,
                                            @RequestParam("status")String status){
            List<Applila> queryApplilaListByStatusList = applilaMapper.queryApplilaByStatus(status);
            model.addAttribute("queryApplilaListByStatus",queryApplilaListByStatusList);
        return "/applila/applilalistByStatus";
    }
    //查看自己申请的实验室
    @RequestMapping("/Myapplila")
    public String queryApplilaByApplicant(Model model,
                                          @RequestParam(required = false,defaultValue="1",value="pageNum")Integer pageNum,
                                          @RequestParam(defaultValue="5",value="pageSize")Integer pageSize){
        User user = (User) SecurityUtils.getSubject().getPrincipal(); // 获取当前登录用户
        String applicant = user.getName();
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
            List<Applila> queryApplilaByApplicantList = applilaMapper.queryApplilaByApplicant(applicant);
                    System.out.println("分页数据：" + queryApplilaByApplicantList);
            //3.使用PageInfo包装查询后的结果,5是连续显示的条数,结果list类型是Page<E>
            PageInfo<Applila> pageInfo = new PageInfo<Applila>(queryApplilaByApplicantList, pageSize);
            //4.使用model/map/modelandview等带回前端
            model.addAttribute("pageInfo", pageInfo);
            model.addAttribute("Myapplila",queryApplilaByApplicantList);
        } finally {
            PageHelper.clearPage(); //清理 ThreadLocal 存储的分页参数,保证线程安全
        }
        return "/applila/Myapplila";
    }
}

