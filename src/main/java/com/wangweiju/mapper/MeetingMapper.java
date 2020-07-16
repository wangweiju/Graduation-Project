package com.wangweiju.mapper;

import com.wangweiju.pojo.Journal;
import com.wangweiju.pojo.Meeting;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface MeetingMapper {
    List<Meeting> queryMeetingList();
    List<Meeting> queryMeetingBySubjectList(String subject);
}
