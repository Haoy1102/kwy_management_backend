package com.kwy.management;

import com.kwy.management.entity.User;
import com.kwy.management.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.mindrot.jbcrypt.BCrypt;

/**
 * @author haoy
 * @description
 * @date 2023/7/12 17:20
 */
@SpringBootTest
public class UserTest {

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
        user.setId(10002L);
        user.setName("管理员2");
        user.setUsername("kwyadmin2");
        user.setPassword("bd4fo7u3v1x");
        user.setStatus(1);
        user.setAccess("管理员");

        String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        user.setPassword(hashedPassword);
//        System.out.println("Hashed Password: " + hashedPassword);
//        boolean passwordMatch = BCrypt.checkpw(user.getPassword(), hashedPassword);
//        System.out.println("Password Match: " + passwordMatch);


        userMapper.updateById(user);
    }



}
