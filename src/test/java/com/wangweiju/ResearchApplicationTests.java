package com.wangweiju;

import com.wangweiju.mapper.UserMapper;
import com.wangweiju.service.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ResearchApplicationTests {

    @Autowired
    UserServiceImpl userService;
    @Test
    void contextLoads() {
         userService.queryUserByName("王为举");
    }

}
