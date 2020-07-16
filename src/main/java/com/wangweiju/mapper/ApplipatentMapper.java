package com.wangweiju.mapper;

import com.wangweiju.pojo.*;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ApplipatentMapper {
    List<Applipatent> queryApplipatentList();
    List<Applipatent> queryApplipatentByApplicant(String applicant);
    List<Applipatent> queryApplipatentByStastus(String status);
    List<Patent> queryPatentList();
    List<Patent> queryPatentByApplicant(String applicant);
    List<Patent> queryPatentListByDate01();
    List<Patent> queryPatentListByDate02();
    List<Patent> queryPatentListByDate03();
    List<Patent> queryPatentListByDate04();
    List<Patent> queryPatentListByDate05();
    List<Patent> queryPatentListByDate06();
    List<Patent> queryPatentListByDate07();
    Applipatent queryApplipatentById(int id);
    int addApplipatent(Applipatents applipatents);
    int updateApplipatent(Applipatent applipatent);
    int addPatent(Applipatent applipatent);
}
