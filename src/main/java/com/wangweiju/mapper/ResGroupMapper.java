package com.wangweiju.mapper;

import com.wangweiju.pojo.ResGroup;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@RequestMapping
@Mapper
public interface ResGroupMapper {
    List<ResGroup> queryResGrouplist();
    List<ResGroup> queryResGrouplistByName(String leader);
    List<ResGroup> queryResGrouplistBytopic(String topic);
    List<ResGroup> queryResGroupListByMember(String member);

    ResGroup queryResGrouplistByTopic(String topic);

    ResGroup queryResGroupById(int id);

    int addResGroup(ResGroup resGroup);

    int updateResGroup(ResGroup resGroup);

    int deleteResGroup(ResGroup resGroup);
}
