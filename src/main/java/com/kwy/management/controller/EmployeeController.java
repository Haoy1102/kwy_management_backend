package com.kwy.management.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.kwy.management.comon.Code;
import com.kwy.management.comon.R;
import com.kwy.management.entity.Employee;
import com.kwy.management.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.mindrot.jbcrypt.BCrypt;
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
 * @date 2023/7/13 15:37
 */
@Slf4j
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/login")
    public R<Employee> login(HttpServletRequest request, @RequestBody Employee employee) {
                /*
        1、md5加密处理
        2、根据页面提交的用户名username查询数据库
        3、如果没有查询到则返回登录失败结果
        4、生密码比矿，如果不一致则返回登录失效结果
        5、查看员工状态，如果为已禁用状态，则返回员工已禁用结果
        6、登录成功，将员工id存入Session并返回登录成功结果
         */
//        1、md5加密处理
        String password=employee.getPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes());

//        2、根据页面提交的用户名username查询数据库
        LambdaQueryWrapper<Employee> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Employee::getUsername, employee.getUsername());
        Employee emp = employeeService.getOne(lqw);

//        3、如果没有查询到则返回登录失败结果
        if (null == emp)
            return R.error("用户不存在，登录失败", Code.LOGIN_FAILED);

//        4、生密码比较，如果不一致则返回登录失效结果
        if (!emp.getPassword().equals(password))
            return R.error("密码错误，登录失败", Code.LOGIN_FAILED);

//        5、查看员工状态，如果为已禁用状态，则返回员工已禁用结果
        if (0 == emp.getStatus())
            return R.error("账号已禁用", Code.LOGIN_FAILED);

//        6、登录成功，将员工id存入Session并返回登录成功结果
        request.getSession().setAttribute("employeeId", emp.getId());
        request.getSession().setAttribute("employeeName", emp.getName());
        return R.success(emp);
    }

    @PostMapping("/logout")
    public R<Boolean> logout(HttpServletRequest request) {
        request.getSession().removeAttribute("employeeId");
        request.getSession().removeAttribute("employeeName");
        return R.success("退出成功");
    }

    @PostMapping
    public R<String> save(@RequestBody Employee employee) {
        //加密密码
        employee.setPassword(DigestUtils.md5DigestAsHex(employee.getPassword().getBytes()));
        return employeeService.save(employee) ?
                R.success("添加成功") :
                R.error("添加失败", Code.SAVE_ERR);
    }
}
