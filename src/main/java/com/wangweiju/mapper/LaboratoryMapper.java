package com.wangweiju.mapper;

import com.wangweiju.pojo.Laboratory;
import com.wangweiju.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface LaboratoryMapper {
    List<Laboratory> queryLaboratoryList();
    List<Laboratory> queryLaboratoryByNum(String num);
    List<Laboratory> queryLaboratoryByStatus(int status);
    Laboratory queryLaboratoryById(int id);
    Laboratory queryLaboratoryBynum(String num);
    int updateLaboratory(String num,int pro_id);
}
