package com.kwy.management.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.kwy.management.comon.Code;
import com.kwy.management.comon.R;
import com.kwy.management.entity.User;
import com.kwy.management.service.UserService;
import com.kwy.management.utils.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author haoy
 * @description
 * @date 2023/7/5 14:11
 */
@Slf4j
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

//    @Autowired
//    private PasswordEncoder passwordEncoder;

    /**
     * 员工登录
     * @param
     * @param user
     * @return
     */
    @PostMapping("/login")
    public R<String> login(HttpSession session, @RequestBody User user){

        String password = user.getPassword();
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername,user.getUsername());
        User authUser = userService.getOne(queryWrapper);

        if (authUser==null||authUser.getStatus()==0){
            return R.error("登录失败", Code.LOGIN_FAILED);
        }
        boolean passwordMatch = BCrypt.checkpw(user.getPassword(), authUser.getPassword());
        if(!passwordMatch){
            return R.error("登录失败",Code.LOGIN_FAILED);
        }

//        登录成功，将员工id存入Session并返回登录成功结果
        TokenUtil tokenUtil = new TokenUtil();
        String token = tokenUtil.generateToken(user.getUsername());
        session.setAttribute("user", user.getId());
        return R.success(token,"登录成功");
    }


}
