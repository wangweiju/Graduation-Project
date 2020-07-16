package com.wangweiju.mapper;

import com.wangweiju.pojo.Journal;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface JournalMapper {
    List<Journal> queryJournalList();
    List<Journal> queryJournalBySubjectList(String subject);
    Journal queryJournalById(int id);
}
