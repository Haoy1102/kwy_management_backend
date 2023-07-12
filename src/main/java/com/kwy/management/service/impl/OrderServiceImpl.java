package com.kwy.management.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kwy.management.comon.Code;
import com.kwy.management.comon.R;
import com.kwy.management.entity.Customer;
import com.kwy.management.entity.Order;
import com.kwy.management.mapper.CustomerMapper;
import com.kwy.management.mapper.OrderMapper;
import com.kwy.management.service.OrderService;
import com.kwy.management.utils.OrderNumberGenerator;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author haoy
 * @description
 * @date 2023/7/8 17:07
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public Boolean addOrder(Order order) {
        String tempcode=OrderNumberGenerator.generateOrderNumber("ZZZZZ");
        order.setOrderId(tempcode);
        if (orderMapper.insert(order)>0){
            LambdaQueryWrapper<Order> lqw = new LambdaQueryWrapper<>();
            lqw.eq(Order::getOrderId,order.getOrderId());
            order=orderMapper.selectOne(lqw);
            order.setOrderId(OrderNumberGenerator.generateOrderNumber(order.getId().toString()));
            orderMapper.updateById(order);
            return true;
        }
        return false;
    }

    @Override
    public IPage getPage(int currentPage, int pageSize, Order order) {
        LambdaQueryWrapper<Order> lqw = new LambdaQueryWrapper<Order>();
        lqw.like(Strings.isNotEmpty(order.getCustomer()),
                Order::getCustomer,order.getCustomer());
        lqw.like(Strings.isNotEmpty(order.getAddress()),
                Order::getAddress,order.getAddress());
        lqw.like(Strings.isNotEmpty(order.getOrderId()),
                Order::getOrderId,order.getOrderId());
        lqw.orderByDesc(Order::getCreateTime);
        IPage page = new Page(currentPage, pageSize);
        orderMapper.selectPage(page,lqw);
        return page;
    }

    @Override
    public List<Order> getOrdersOneYearByCustomerId(Long customerId) {

        // 获取当前日期时间
        LocalDateTime currentDateTime = LocalDateTime.now();

        // 计算一年前的日期时间
        LocalDateTime oneYearAgo = currentDateTime.minusYears(1);

        //获取一年内所有的订单数据
        LambdaQueryWrapper<Order> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Order::getCustomerId, customerId)
                .ge(Order::getCreateTime, oneYearAgo)
                .le(Order::getCreateTime, currentDateTime);

        return orderMapper.selectList(lqw);
    }
}
