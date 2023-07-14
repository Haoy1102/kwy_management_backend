package com.kwy.management.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kwy.management.entity.Customer;
import com.kwy.management.entity.Employee;
import com.kwy.management.mapper.CustomerMapper;
import com.kwy.management.mapper.EmployeeMapper;
import com.kwy.management.service.EmployeeService;
import org.springframework.stereotype.Service;

/**
 * @author haoy
 * @description
 * @date 2023/7/13 15:39
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {

}
