package com.wangweiju.mapper;


import com.wangweiju.pojo.Applila;
import com.wangweiju.pojo.Applilas;
import com.wangweiju.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ApplilaMapper {

    List<Applila> queryApplilaList();
    List<Applila> queryApplilaByStatus(String status);
    List<Applila> queryApplilaByApplicant(String applicant);
    Applila queryApplilaById(int id);
    int addApplila(Applilas applilas);
    int rejectApplila(Applila applila);
}
