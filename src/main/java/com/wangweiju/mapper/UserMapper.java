package com.wangweiju.mapper;

import com.wangweiju.pojo.User;
import com.wangweiju.pojo.Users;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface UserMapper {

     List<User> queryUserList();

     User queryUserByName(String name);

    User queryUserById(int id);

    int addUser(Users users);

    int updateUser(User user);

    int deleteUser(int id);

}
