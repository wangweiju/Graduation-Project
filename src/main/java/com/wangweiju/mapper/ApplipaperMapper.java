package com.wangweiju.mapper;


import com.wangweiju.pojo.Applipaper;
import com.wangweiju.pojo.Applipapers;
import com.wangweiju.pojo.Paper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ApplipaperMapper {
    List<Applipaper> queryApplipaperList();
    List<Applipaper> queryApplipaperByAuthor(String author);
    List<Applipaper> queryApplipaperByStastus(String status);
    List<Paper> queryPaperList();
    List<Paper> queryPaperByAuthor(String author);
    List<Paper> queryPaperListByDate01();
    List<Paper> queryPaperListByDate02();
    List<Paper> queryPaperListByDate03();
    List<Paper> queryPaperListByDate04();
    List<Paper> queryPaperListByDate05();
    List<Paper> queryPaperListByDate06();
    List<Paper> queryPaperListByDate07();
    Applipaper queryApplipaperById(int id);
    int addApplipaper(Applipapers applipapers);
    int updateApplipaper(Applipaper applipaper);
    int addPaper(Applipaper applipaper);
}
