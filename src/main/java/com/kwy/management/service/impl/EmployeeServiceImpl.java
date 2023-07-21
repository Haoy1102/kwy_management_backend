package com.kwy.management.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kwy.management.entity.Customer;
import com.kwy.management.entity.Employee;
import com.kwy.management.mapper.CustomerMapper;
import com.kwy.management.mapper.EmployeeMapper;
import com.kwy.management.service.EmployeeService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author haoy
 * @description
 * @date 2023/7/13 15:39
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public IPage<Employee> getPage(int currentPage, int pageSize, Employee employee) {
        LambdaQueryWrapper<Employee> lqw = new LambdaQueryWrapper<Employee>();
        lqw.like(Strings.isNotEmpty(employee.getName()),
                Employee::getName, employee.getName());
        IPage page = new Page(currentPage, pageSize);
        employeeMapper.selectPage(page, lqw);
        return page;
    }

    @Override
    public boolean isAdmin(Long employeeId) {
        Employee employee = employeeMapper.selectById(employeeId);
        return employee.getStatus() == 2;
    }
}
