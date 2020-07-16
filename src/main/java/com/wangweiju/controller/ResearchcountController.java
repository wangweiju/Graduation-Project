package com.wangweiju.controller;

import com.wangweiju.mapper.ApplibookMapper;
import com.wangweiju.mapper.ApplipaperMapper;
import com.wangweiju.mapper.ApplipatentMapper;
import com.wangweiju.pojo.Applibook;
import com.wangweiju.pojo.Book;
import com.wangweiju.pojo.Paper;
import com.wangweiju.pojo.Patent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class ResearchcountController {
    @Autowired
    private ApplibookMapper applibookMapper;
    @Autowired
    private ApplipaperMapper applipaperMapper;
    @Autowired
    private ApplipatentMapper applipatentMapper;
    @RequestMapping("/researchcount")
    public String researchcount(Model model){
      //获取2014年论文，专利，专著数量
        //2014年论文数量
      List<Paper> paperList01 = applipaperMapper.queryPaperListByDate01();
        //2014年专利数量
      List<Patent> patentList01 = applipatentMapper.queryPatentListByDate01();
        //2014年专著数量
      List<Book> bookList01 = applibookMapper.queryBookListByDate01();
        //2014年科研成果总和
        int count01 = paperList01.size()+patentList01.size()+bookList01.size();
        System.out.println("2014年科研成果总和为："+count01);


        //获取2015年论文，专利，专著数量
        //2015年论文数量
        List<Paper> paperList02 = applipaperMapper.queryPaperListByDate02();
        //2015年专利数量
        List<Patent> patentList02 = applipatentMapper.queryPatentListByDate02();
        //2015年专著数量
        List<Book> bookList02 = applibookMapper.queryBookListByDate02();
        //2015年科研成果总和
        int count02 = paperList02.size()+patentList02.size()+bookList02.size();
        System.out.println("2015年科研成果总和为："+count02);


        //获取2016年论文，专利，专著数量
        //2016年论文数量
        List<Paper> paperList03 = applipaperMapper.queryPaperListByDate03();
        //2016年专利数量
        List<Patent> patentList03 = applipatentMapper.queryPatentListByDate03();
        //2016年专著数量
        List<Book> bookList03 = applibookMapper.queryBookListByDate03();
        //2016年科研成果总和
        int count03 = paperList03.size()+patentList03.size()+bookList03.size();
        System.out.println("2016年科研成果总和为："+count03);



        //获取2017年论文，专利，专著数量
        //2017年论文数量
        List<Paper> paperList04 = applipaperMapper.queryPaperListByDate04();
        //2017年专利数量
        List<Patent> patentList04 = applipatentMapper.queryPatentListByDate04();
        //2017年专著数量
        List<Book> bookList04 = applibookMapper.queryBookListByDate04();
        //2017年科研成果总和
        int count04 = paperList04.size()+patentList04.size()+bookList04.size();
        System.out.println("2017年科研成果总和为："+count04);



        //获取2018年论文，专利，专著数量
        //2018年论文数量
        List<Paper> paperList05 = applipaperMapper.queryPaperListByDate05();
        //2018年专利数量
        List<Patent> patentList05 = applipatentMapper.queryPatentListByDate05();
        //2018年专著数量
        List<Book> bookList05 = applibookMapper.queryBookListByDate05();
        //2018年科研成果总和
        int count05 = paperList05.size()+patentList05.size()+bookList05.size();
        System.out.println("2018年科研成果总和为："+count05);

        //获取2019年论文，专利，专著数量
        //2019年论文数量
        List<Paper> paperList06 = applipaperMapper.queryPaperListByDate06();
        //2019年专利数量
        List<Patent> patentList06 = applipatentMapper.queryPatentListByDate06();
        //2019年专著数量
        List<Book> bookList06 = applibookMapper.queryBookListByDate06();
        //2019年科研成果总和
        int count06 = paperList06.size()+patentList06.size()+bookList06.size();
        System.out.println("2019年科研成果总和为："+count06);

        //获取2020年论文，专利，专著数量
        //2020年论文数量
        List<Paper> paperList07 = applipaperMapper.queryPaperListByDate07();
        //2020年专利数量
        List<Patent> patentList07 = applipatentMapper.queryPatentListByDate07();
        //2020年专著数量
        List<Book> bookList07 = applibookMapper.queryBookListByDate07();
        //2020年科研成果总和
        int count07 = paperList07.size()+patentList07.size()+bookList07.size();
        System.out.println("2020年科研成果总和为："+count07);

        //将数据传到求前端
        int num = 0;
        model.addAttribute("num",num);
        model.addAttribute("count01",count01);
        model.addAttribute("count02",count02);
        model.addAttribute("count03",count03);
        model.addAttribute("count04",count04);
        model.addAttribute("count05",count05);
        model.addAttribute("count06",count06);
        model.addAttribute("count07",count07);
        return "/researchcount/researchcount";
    }
}
