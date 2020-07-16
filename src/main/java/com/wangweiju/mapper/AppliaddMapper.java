package com.wangweiju.mapper;

import com.wangweiju.pojo.Appliadd;
import com.wangweiju.pojo.Appligro;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface AppliaddMapper {
    List<Appliadd> queryAppliaddList(String leader);
    List<Appliadd> queryAppliaddListByName(String name);
    List<Appliadd> queryAppliaddListByStatus(String status,String leader);
    Appliadd queryAppliaddById(int id);
    int insertAppliadd(String topic,String leader,String member,String name);
    int rejectAppliadd(Appliadd appliadd);
    int agreeAppliadd(String member,String topic);
}
