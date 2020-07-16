package com.wangweiju.mapper;

import com.wangweiju.pojo.Subject;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface SubjectMapper {
    List<Subject> querySubjectList();
}
