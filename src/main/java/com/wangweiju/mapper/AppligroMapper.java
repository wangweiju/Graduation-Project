package com.wangweiju.mapper;

import com.wangweiju.pojo.Appligro;
import com.wangweiju.pojo.Appligros;
import com.wangweiju.pojo.Applila;
import com.wangweiju.pojo.ReGroups;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface AppligroMapper {
    List<Appligro> queryAppligroList();
    Appligro queryAppligroById(int id);
    List<Appligro> queryAppligroByStatus(String status);
    List<Appligro> queryAppligroByName(String name);
    int addApppligro(Appligros appligros);
    int rejectAppligro(Appligro appligro);
    int addgroup(Appligro Appligro);
}
