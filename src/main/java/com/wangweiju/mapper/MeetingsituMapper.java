package com.wangweiju.mapper;

import com.wangweiju.pojo.Meetingcount;
import com.wangweiju.pojo.Meetingsitu;
import com.wangweiju.pojo.Meetingsitus;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface MeetingsituMapper {
    List<Meetingsitu> queryMeetingsituList();
    List<Meetingsitu> queryMeetingsituByName(String name);
    int addMeetingsitu(Meetingsitus meetingsitus);
    int addMeetingcount(Meetingcount meetingcount);
}
