package com.kwy.management.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kwy.management.entity.Customer;
import com.kwy.management.entity.Order;

import java.util.List;

/**
 * @author haoy
 * @description
 * @date 2023/7/8 17:06
 */
public interface OrderService extends IService<Order> {

    public Boolean addOrder(Order order);

    public IPage getPage(int currentPage, int pageSize, Order order);

    List<Order> getOrdersOneYearByCustomerId(Long customerId);
}
