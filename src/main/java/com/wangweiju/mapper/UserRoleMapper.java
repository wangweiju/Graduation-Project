package com.wangweiju.mapper;

import com.wangweiju.pojo.UserRole;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface UserRoleMapper {
    List<UserRole> queryUserRoleList();
    List<UserRole> queryUserListByRole(String perms);
    List<UserRole> queryUserListByName(String name);
}
