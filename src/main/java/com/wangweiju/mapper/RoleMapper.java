package com.wangweiju.mapper;

import com.wangweiju.pojo.Role;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface RoleMapper {

    List<Role> queryRoleList();

    List<Role> queryRoleListBylevel(int level);

    Role queryRoleById(int id);

    int addRole(Role role);

    int updateRole(Role role);

    int deleteRole(int id);
}
