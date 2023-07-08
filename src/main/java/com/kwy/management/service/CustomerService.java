package com.kwy.management.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kwy.management.entity.Customer;

/**
 * @author haoy
 * @description
 * @date 2023/7/7 14:18
 */
public interface CustomerService extends IService<Customer> {
    public IPage getPage(int currentPage,int pageSize,Customer customer);
}
