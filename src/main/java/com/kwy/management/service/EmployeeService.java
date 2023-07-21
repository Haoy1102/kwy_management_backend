package com.kwy.management.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kwy.management.entity.Employee;
import com.kwy.management.entity.GoodsRecord;

/**
 * @author haoy
 * @description
 * @date 2023/7/13 15:39
 */
public interface EmployeeService extends IService<Employee> {
    IPage<Employee> getPage(int currentPage, int pageSize, Employee employee);

    boolean isAdmin(Long employeeId);
}
