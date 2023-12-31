package com.kwy.management.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kwy.management.dto.AccountDto;
import com.kwy.management.dto.OrderAddDto;
import com.kwy.management.entity.Order;
import com.kwy.management.entity.OrderDetail;
import com.kwy.management.entity.PaymentDetail;
import com.kwy.management.mapper.OrderDetailMapper;
import com.kwy.management.mapper.OrderMapper;
import com.kwy.management.mapper.PaymentDetailMapper;
import com.kwy.management.service.OrderService;
import com.kwy.management.utils.OrderNumberGenerator;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
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

    @Autowired
    private OrderDetailMapper orderDetailMapper;

    @Autowired
    private PaymentDetailMapper paymentDetailMapper;

    @Override
    @Transactional
    public Boolean addOrder(OrderAddDto orderAddDto) {
        /*
            1. 订单表 计算金额等
            2. 订单明细表 记录
         */

        List<OrderDetail> details = orderAddDto.getOrderDetails();
        Order order = new Order();
        BeanUtils.copyProperties(orderAddDto, order);

        //        1. 订单表 计算金额等
        double totalAmount = 0.0;
        if (!details.isEmpty()) {
            for (OrderDetail orderDetail : details) {
                totalAmount += orderDetail.getAmount();
            }
        }
        order.setAmount(totalAmount);
        orderMapper.insert(order);
        order.setOrderId(OrderNumberGenerator.generateOrderNumber(order.getId().toString()));
        orderMapper.updateById(order);
        //         2.订单明细表 记录
        for (OrderDetail detail : details) {
            detail.setOrderId(order.getOrderId());
            orderDetailMapper.insert(detail);
        }
        return true;
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
        lqw.like(Strings.isNotEmpty(order.getPeople()),
                Order::getPeople, order.getPeople());
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
                .le(Order::getCreateTime, currentDateTime)
                .orderByDesc(Order::getCreateTime);

        return orderMapper.selectList(lqw);
    }

    public AccountDto getAcountThisMonth() {
        /*
            1. 本月总成交额     本月订单(非作废，非返场)的总成交额
            2. 本月总交付金额    本月订单(非作废，非返场)的出货额度
            3. 本月总入账额
         */
        AccountDto accountDto = new AccountDto();
        LocalDateTime currentDateTime = LocalDateTime.now();
        LocalDateTime firstDayOfMonth = currentDateTime.withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0).withNano(0);
        LocalDateTime lastDayOfMonth = currentDateTime.with(TemporalAdjusters.lastDayOfMonth()).withHour(23).withMinute(59).withSecond(59).withNano(999999999);

//        // 获取当前日期时间
//        LocalDateTime currentDateTime = LocalDateTime.now();
//
//        // 计算数月前日期时间
//        LocalDateTime monthsAgo = currentDateTime.minusMonths(month);

        //获取本月内所有的订单数据
        LambdaQueryWrapper<Order> lqw = new LambdaQueryWrapper<>();
        lqw.ge(Order::getCreateTime, firstDayOfMonth)
                .le(Order::getCreateTime, lastDayOfMonth);
        List<Order> orders = orderMapper.selectList(lqw);


//      1. 本月总成交额     本月订单(非作废，非返场)的总成交额
        Integer[] targetStatus1 = {1, 2, 3, 4, 5}; // 目标订单状态
        double totalAmount = 0.0;
        for (Order order : orders) {
            if (Arrays.asList(targetStatus1).contains(order.getStatus())) {
                totalAmount += order.getAmount();
            }
        }
        accountDto.setTotalAmount(totalAmount);

//        2. 现存订单额   （目前未完成(非作废，非返场)的订单额度）
//        Integer[] targetStatus2 = {1, 2, 3, 4}; // 目标订单状态
//        double totalAmountCurrent = 0.0;
//        for (Order order : orders) {
//            if (Arrays.asList(targetStatus2).contains(order.getStatus())) {
//                totalAmountCurrent += order.getAmount();
//            }
//        }
//        accountDto.setTotalAmountCurrent(totalAmountCurrent);

//      2.本月总交付金额    本月订单(非作废，非返场)的出货额度
        Integer[] targetStatus3 = {1, 2, 3, 4, 5}; // 目标订单状态
        double totalAmountDelivered = 0.0;
        for (Order order : orders) {
            if (Arrays.asList(targetStatus3).contains(order.getStatus())) {
                totalAmountDelivered += order.getTotalDelivered();
            }
        }
        accountDto.setTotalAmountDelivered(totalAmountDelivered);

//      4. 未出货额
//        accountDto.setTotalAmountNoDelivered(totalAmountCurrent - totalAmountDelivered);

//      3.本月总入账额
        Integer[] targetStatus4 = {1, 2, 3, 4, 5}; // 目标订单状态
        double totalPayment = 0.0;
        for (Order order : orders) {
            if (Arrays.asList(targetStatus4).contains(order.getStatus())) {
                totalPayment += order.getTotalPayment();
            }
        }
        accountDto.setTotalAmoutPayment(totalPayment);



//      5. 待入账总金额
//        Integer[] targetStatus5 = {1, 2, 3, 4}; // 目标订单状态
//        double totalPayment = 0.0;
//        for (Order order : orders) {
//            if (Arrays.asList(targetStatus5).contains(order.getStatus())) {
//                totalPayment += order.getTotalPayment();
//            }
//        }
//        accountDto.setTotalAmountDebt(totalAmountCurrent - totalPayment);


        return accountDto;
    }

    @Override
    public Boolean updateSelfByOrderId(String orderId) {
        /*
        1.计算订单金额
        2.计算交付进度
        3.计算已回金额
         */
        LambdaQueryWrapper<Order> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Order::getOrderId, orderId);
        Order order = orderMapper.selectOne(wrapper);

        LambdaQueryWrapper<OrderDetail> wrapper1 = new LambdaQueryWrapper<>();
        wrapper1.eq(OrderDetail::getOrderId, orderId);
        List<OrderDetail> orderDetails = orderDetailMapper.selectList(wrapper1);

        LambdaQueryWrapper<PaymentDetail> wrapper2 = new LambdaQueryWrapper<>();
        wrapper2.eq(PaymentDetail::getOrderId, orderId);
        List<PaymentDetail> paymentDetails = paymentDetailMapper.selectList(wrapper2);

//        1.计算订单金额
        Double totalAmount = 0.0;
        Double totalDelivered = 0.0;
        if (!orderDetails.isEmpty()) {
            for (OrderDetail detail : orderDetails) {
                totalAmount += detail.getAmount();
                //已发货
                if (detail.getIsDelivered() == 1)
                    totalDelivered += detail.getAmount();
            }
        }
        order.setAmount(totalAmount);
//        2.计算交付进度
        int deliveryProgress=0;
        if (totalAmount!=0.0){
            deliveryProgress=(int)(totalDelivered/totalAmount*100);
        }
        order.setTotalDelivered(totalDelivered);
        order.setDeliveryProgress(deliveryProgress);
//        3.计算已回金额
        Double totalPayment = 0.0;
        if (!paymentDetails.isEmpty()) {
            for (PaymentDetail detail : paymentDetails) {
                totalPayment += detail.getAmount();
            }
        }
        order.setTotalPayment(totalPayment);
//        4.更新数据库
        orderMapper.updateById(order);
        return true;
    }
}
