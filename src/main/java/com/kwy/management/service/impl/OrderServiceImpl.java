package com.kwy.management.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kwy.management.comon.Code;
import com.kwy.management.comon.R;
import com.kwy.management.dto.AccountDto;
import com.kwy.management.dto.CustomerDto;
import com.kwy.management.entity.Customer;
import com.kwy.management.entity.Order;
import com.kwy.management.mapper.CustomerMapper;
import com.kwy.management.mapper.OrderMapper;
import com.kwy.management.service.OrderService;
import com.kwy.management.utils.OrderNumberGenerator;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
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
        String tempcode = OrderNumberGenerator.generateOrderNumber("ZZZZZ");
        order.setOrderId(tempcode);
        if (orderMapper.insert(order) > 0) {
            LambdaQueryWrapper<Order> lqw = new LambdaQueryWrapper<>();
            lqw.eq(Order::getOrderId, order.getOrderId());
            order = orderMapper.selectOne(lqw);
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
                Order::getCustomer, order.getCustomer());
        lqw.like(Strings.isNotEmpty(order.getAddress()),
                Order::getAddress, order.getAddress());
        lqw.like(Strings.isNotEmpty(order.getOrderId()),
                Order::getOrderId, order.getOrderId());
        lqw.orderByDesc(Order::getCreateTime);
        IPage page = new Page(currentPage, pageSize);
        orderMapper.selectPage(page, lqw);
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

    @Override
    public AccountDto getAcountSimple(Long month) {
        /*
            1. 近n月订单总额        (非作废，非返场)的年总订单额度
            2. 未完成订单金额        目前未完成(非作废，非返场)的订单额度）
            3. 已出货额          目前未完成(非作废，非返场)的订单 订单金额*出货进度
            4. 未出货额
            5. 待入账总金额          （待回款状态下）现存订单额度-已回金额
//          6. 原材料总资产(放在原料模块)       (非作废，非返场)现存订单额度-已回金额
         */

        AccountDto accountDto = new AccountDto();

        // 获取当前日期时间
        LocalDateTime currentDateTime = LocalDateTime.now();

        // 计算数月前日期时间
        LocalDateTime monthsAgo = currentDateTime.minusMonths(month);

        //获取数月内所有的订单数据
        LambdaQueryWrapper<Order> lqw = new LambdaQueryWrapper<>();
        lqw.ge(Order::getCreateTime, monthsAgo)
                .le(Order::getCreateTime, currentDateTime);
        List<Order> orders = orderMapper.selectList(lqw);


//        1. 总成交额
        Integer[] targetStatus1 = {1, 2, 3, 4, 5}; // 目标订单状态
        double totalAmount = 0.0;
        for (Order order : orders) {
            if (Arrays.asList(targetStatus1).contains(order.getStatus())) {
                totalAmount += order.getAmount();
            }
        }
        accountDto.setTotalAmount(totalAmount);

//        2. 现存订单额   （目前未完成(非作废，非返场)的订单额度）
        Integer[] targetStatus2 = {1, 2, 3, 4}; // 目标订单状态
        double totalAmountCurrent = 0.0;
        for (Order order : orders) {
            if (Arrays.asList(targetStatus2).contains(order.getStatus())) {
                totalAmountCurrent += order.getAmount();
            }
        }
        accountDto.setTotalAmountCurrent(totalAmountCurrent);

//      3.已出货额    目前未完成(非作废，非返场)的订单 订单金额*出货进度
        Integer[] targetStatus3 = {1, 2, 3, 4}; // 目标订单状态
        double totalAmountDelivered = 0.0;
        for (Order order : orders) {
            if (Arrays.asList(targetStatus3).contains(order.getStatus())) {
                totalAmountDelivered += order.getAmount() * order.getDeliveryProgress() / 100;
            }
        }
        accountDto.setTotalAmountDelivered(totalAmountDelivered);

//      4. 未出货额
        accountDto.setTotalAmountNoDelivered(totalAmountCurrent-totalAmountDelivered);

//      5. 待入账总金额
        Integer[] targetStatus5 = {1, 2, 3, 4}; // 目标订单状态
        double totalPayment = 0.0;
        for (Order order : orders) {
            if (Arrays.asList(targetStatus5).contains(order.getStatus())) {
                totalPayment += order.getTotalPayment();
            }
        }
        accountDto.setTotalAmountDebt(totalAmountCurrent-totalPayment);



        return accountDto;
    }
}
