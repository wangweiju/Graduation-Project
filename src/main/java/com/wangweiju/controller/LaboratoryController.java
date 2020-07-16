package com.wangweiju.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wangweiju.mapper.LaboratoryMapper;
import com.wangweiju.pojo.Applila;
import com.wangweiju.pojo.Laboratory;
import com.wangweiju.pojo.ResGroup;
import com.wangweiju.pojo.User;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class LaboratoryController {
    @Autowired
    private LaboratoryMapper laboratoryMapper;

    //查询所有实验室
    @RequestMapping("/laboratories")
    public String queryLaboratoryList(Model model,
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
            List<Laboratory> laboratoryList = laboratoryMapper.queryLaboratoryList();
            System.out.println("分页数据：" + laboratoryList);
            //3.使用PageInfo包装查询后的结果,5是连续显示的条数,结果list类型是Page<E>
            PageInfo<Laboratory> pageInfo = new PageInfo<Laboratory>(laboratoryList, pageSize);
            //4.使用model/map/modelandview等带回前端
            model.addAttribute("pageInfo", pageInfo);
            model.addAttribute("laboratories",laboratoryList);
        } finally {
            PageHelper.clearPage(); //清理 ThreadLocal 存储的分页参数,保证线程安全
        }
/*
List<Laboratory> laboratoryList = laboratoryMapper.queryLaboratoryList();
        System.out.println("所有实验室的信息："+laboratoryList);
        model.addAttribute("laboratories",laboratoryList);
 */

        return "/laboratory/laboratorylist";
    }

    //按照实验室名称查询实验室

  @RequestMapping("/queryLaboratoryByNum")
    public String  queryLaboratoryByNum(Model model,
                                       @RequestParam("num")String  num,
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
          List<Laboratory> queryLaboratoryByNumList = laboratoryMapper.queryLaboratoryByNum(num);
          System.out.println("按照实验室名称查询为："+queryLaboratoryByNumList);
          System.out.println("分页数据：" + queryLaboratoryByNumList);
          //3.使用PageInfo包装查询后的结果,5是连续显示的条数,结果list类型是Page<E>
          PageInfo<Laboratory> pageInfo = new PageInfo<Laboratory>(queryLaboratoryByNumList, pageSize);
          //4.使用model/map/modelandview等带回前端
          model.addAttribute("pageInfo", pageInfo);
          model.addAttribute("laboratories",queryLaboratoryByNumList);
      } finally {
          PageHelper.clearPage(); //清理 ThreadLocal 存储的分页参数,保证线程安全
      }
      return "/laboratory/laboratorylist";
  }

  //按照实验室状态查询
    @RequestMapping("/queryLaboratoryByStatus")
    public String  queryLaboratoryByStatus(Model model,
                                        @RequestParam("status")int  status){
            List<Laboratory> queryLaboratoryByStatusList = laboratoryMapper.queryLaboratoryByStatus(status);
            System.out.println("按照实验室名称查询为："+queryLaboratoryByStatusList);
            model.addAttribute("queryLaboratoryByStatus",queryLaboratoryByStatusList);
        return "/laboratory/laboratorylistByStatus";
    }
    //申请实验室Applila
    @GetMapping("/toaddapplila/{id}")
    public String toaddApplila(@PathVariable("id")int id,Model model){
        //查出原来的数据
        Laboratory laboratory = laboratoryMapper.queryLaboratoryById(id);
        System.out.println("根据id查出的数据:"+laboratory);
        model.addAttribute("applila",laboratory);
        return "/laboratory/applila";
    }
}
