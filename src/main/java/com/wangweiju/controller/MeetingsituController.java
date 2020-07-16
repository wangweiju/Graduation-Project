package com.wangweiju.controller;

import com.wangweiju.mapper.MeetingsituMapper;
import com.wangweiju.pojo.Meetingcount;
import com.wangweiju.pojo.Meetingsitu;
import com.wangweiju.pojo.Meetingsitus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MeetingsituController {
    @Autowired
    private MeetingsituMapper meetingsituMapper;
    //查询所有录入的返校情况
    @RequestMapping("/meetingsitus")
    public String Meetingsitu(Model model){
        List<Meetingsitu> meetingsituList = meetingsituMapper.queryMeetingsituList();
        model.addAttribute("meetingsitus",meetingsituList);
        return "/meeting/meetingsitu";
    }
    //添加录入的会议情况
    @RequestMapping("/addMeetingsitu")
    public String addMeetingsitu(Meetingsitus meetingsitus){
        meetingsituMapper.addMeetingsitu(meetingsitus);
        return  "redirect:/meetingsitus";
    }
    //通过会议名称查询
    @RequestMapping("/queryMeetingsituByName")
    public String queryMeetingsituByName(Model model,
                                        @RequestParam("name")String  name){
        List<Meetingsitu> meetingsituList = meetingsituMapper.queryMeetingsituByName(name);
        model.addAttribute("meetingsitus",meetingsituList);
        return "/meeting/meetingsitu";
    }
    //统计
    @RequestMapping("/meetingcount")
    public String count(Model model){

        List<Meetingsitu> meetingsituList = meetingsituMapper.queryMeetingsituList();
       int m = meetingsituList.size();
       for(int i = 0;i <m; i++){
              Meetingsitu meetingsitu =  meetingsituList.get(i);
              int count = meetingsitu.getYnum()-meetingsitu.getSnum();
              meetingsitu.setCount(count);
       }
       System.out.println("最后的List："+meetingsituList);
       model.addAttribute("counts",meetingsituList);
       return "/meeting/meettingcount";
    }
}
