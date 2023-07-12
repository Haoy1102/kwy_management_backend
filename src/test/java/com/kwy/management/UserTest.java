package com.kwy.management;

import com.kwy.management.entity.User;
import com.kwy.management.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author haoy
 * @description
 * @date 2023/7/12 17:20
 */
@SpringBootTest
public class UserTest {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserMapper userMapper;

    @Test
    void registerUser() {

//        User user = new User();
//        user.setName("管理员2");
//        user.setUsername("kwyadmin2");
//        user.setPassword("bd4fo7u3v1x");
//        user.setStatus(1);
//        user.setAccess("管理员");

        User user = new User();
        user.setId(10000L);
        user.setName("超级管理员");
        user.setUsername("admin");
        user.setPassword("123456");
        user.setStatus(1);
        user.setAccess("管理员");

        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        userMapper.updateById(user);
    }



}
