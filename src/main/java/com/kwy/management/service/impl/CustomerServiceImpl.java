package com.kwy.management.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kwy.management.entity.Customer;
import com.kwy.management.mapper.CustomerMapper;
import com.kwy.management.service.CustomerService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.print.Book;

/**
 * @author haoy
 * @description
 * @date 2023/7/7 14:19
 */
@Service
public class CustomerServiceImpl extends ServiceImpl<CustomerMapper, Customer> implements CustomerService {

    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public IPage getPage(int currentPage, int pageSize,Customer customer) {
        LambdaQueryWrapper<Customer> lqw = new LambdaQueryWrapper<Customer>();
        lqw.like(Strings.isNotEmpty(customer.getCustomer()),
                Customer::getCustomer,customer.getCustomer());
        lqw.like(Strings.isNotEmpty(customer.getAddress()),
                Customer::getAddress,customer.getAddress());
        IPage page = new Page(currentPage, pageSize);
        customerMapper.selectPage(page, lqw);
        return page;
    }
}
