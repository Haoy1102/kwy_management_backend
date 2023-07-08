package com.kwy.management.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.kwy.management.comon.Code;
import com.kwy.management.comon.R;
import com.kwy.management.entity.Employee;
import com.kwy.management.service.EmployeeService;
import com.kwy.management.utils.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author haoy
 * @description
 * @date 2023/7/5 14:11
 */
@Slf4j
@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    /**
     * 员工登录
     * @param request
     * @param employee
     * @return
     */
    @PostMapping("/login")
    public R<String> login(HttpServletRequest request, @RequestBody Employee employee){

        String password = employee.getPassword();
        password= DigestUtils.md5DigestAsHex(password.getBytes());

        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Employee::getUsername,employee.getUsername());
        Employee emp = employeeService.getOne(queryWrapper);

        if (emp==null){
            return R.error("登录失败", Code.LOGIN_FAILED);
        }
        if(!emp.getPassword().equals(password)){
            return R.error("登录失败",Code.LOGIN_FAILED);
        }

//        request.getSession().setAttribute("employee",emp.getId());

//        LoginTest loginTest = new LoginTest();
//        MenuItem menuItem = new MenuItem();
//        menuItem.setPath("/home");
//        menuItem.setName("home");
//        menuItem.setLabel("首页");
//        menuItem.setIcon("s-home");
//        menuItem.setUrl("Home.vue");
//
//        ArrayList<MenuItem> menu = new ArrayList<>();
//        menu.add(menuItem);
//
//        loginTest.setMenu(menu);

//        登录成功，将员工id存入Session并返回登录成功结果
        TokenUtil tokenUtil = new TokenUtil();
        String token = tokenUtil.generateToken(employee.getUsername());


        return R.success(token);
    }

    @PostMapping("/test")
    public R<String> test(){
        return R.success("访问成功");
    }
}
