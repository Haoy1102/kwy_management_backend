package com.kwy.management.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kwy.management.dto.CustomerDto;
import com.kwy.management.entity.Customer;
import com.kwy.management.entity.Order;
import com.kwy.management.mapper.CustomerMapper;
import com.kwy.management.mapper.OrderMapper;
import com.kwy.management.service.CustomerService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * @author haoy
 * @description
 * @date 2023/7/7 14:19
 */
@Service
public class CustomerServiceImpl extends ServiceImpl<CustomerMapper, Customer> implements CustomerService {

    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public IPage getPage(int currentPage, int pageSize, Customer customer) {
        LambdaQueryWrapper<Customer> lqw = new LambdaQueryWrapper<Customer>();
        lqw.like(Strings.isNotEmpty(customer.getCustomer()),
                Customer::getCustomer, customer.getCustomer());
        lqw.like(Strings.isNotEmpty(customer.getAddress()),
                Customer::getAddress, customer.getAddress());
        IPage page = new Page(currentPage, pageSize);
        customerMapper.selectPage(page, lqw);
        return page;
    }

    /**
     * 计算得到Customer的 1. 年总成交额 2. 现存订单额 3. 已出货额 4. 欠缴等附加属性
     *
     * @param id
     * @return
     */
    @Override
    public CustomerDto getDetails(Long id) {

        /*
            1. 年总成交额        (非作废，非返场)的年总订单额度
            2. 现存订单额        目前未完成(非作废，非返场)的订单额度）
            3. 已出货额          目前未完成(非作废，非返场)的订单 订单金额*出货进度
            4. 欠款额度          （待回款状态下）现存订单额度-已回金额
//          5. 总待支付金额       (非作废，非返场)现存订单额度-已回金额
         */
        CustomerDto customerDto = new CustomerDto();
        Customer customer = customerMapper.selectById(id);
        BeanUtils.copyProperties(customer, customerDto);

        // 获取当前日期时间
        LocalDateTime currentDateTime = LocalDateTime.now();

        // 计算一年前的日期时间
        LocalDateTime oneYearAgo = currentDateTime.minusYears(1);

        //获取一年内所有的订单数据
        LambdaQueryWrapper<Order> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Order::getCustomerId, id)
                .ge(Order::getCreateTime, oneYearAgo)
                .le(Order::getCreateTime, currentDateTime);
        List<Order> orders = orderMapper.selectList(lqw);


//        1. 年总成交额
        Integer[] targetStatus1 = {1, 2, 3, 4, 5}; // 目标订单状态
        double totalAmountPerYear = 0.0;
        for (Order order : orders) {
            if (Arrays.asList(targetStatus1).contains(order.getStatus())) {
                totalAmountPerYear += order.getAmount();
            }
        }
        customerDto.setTotalAmountPerYear(totalAmountPerYear);

//        2. 现存订单额   （目前未完成(非作废，非返场)的订单额度）
        Integer[] targetStatus2 = {1, 2, 3, 4}; // 目标订单状态
        double totalAmountCurrent = 0.0;
        for (Order order : orders) {
            if (Arrays.asList(targetStatus2).contains(order.getStatus())) {
                totalAmountCurrent += order.getAmount();
            }
        }
        customerDto.setTotalAmountCurrent(totalAmountCurrent);

//      3.已出货额    目前未完成(非作废，非返场)的订单 订单金额*出货进度
        Integer[] targetStatus3 = {1, 2, 3, 4}; // 目标订单状态
        double totalAmountDelivered = 0.0;
        for (Order order : orders) {
            if (Arrays.asList(targetStatus3).contains(order.getStatus())) {
                totalAmountDelivered += order.getAmount() * order.getDeliveryProgress() / 100;
            }
        }
        customerDto.setTotalAmountDelivered(totalAmountDelivered);

//      4.欠款额度    (待回款状态下) 现存订单额度-已回金额
        Integer[] targetStatus4 = {4}; // 目标订单状态
        double totalPayment4Completed = 0.0;
        double totalAmount4Completed = 0.0;
        for (Order order : orders) {
            if (Arrays.asList(targetStatus4).contains(order.getStatus())) {
                totalAmount4Completed += order.getAmount();
                totalPayment4Completed += order.getTotalPayment();
            }
        }
        customerDto.setTotalAmountDebt4Completed(totalAmount4Completed-totalPayment4Completed);

//      5.总待支付金额    现存订单额度-已回金额
        Integer[] targetStatus5 = {1, 2, 3, 4}; // 目标订单状态
        double totalPayment = 0.0;
        for (Order order : orders) {
            if (Arrays.asList(targetStatus5).contains(order.getStatus())) {
                totalPayment += order.getTotalPayment();
            }
        }
        double totalAmountDebt = customerDto.getTotalAmountCurrent() - totalPayment;
        customerDto.setTotalAmountDebt(totalAmountDebt);

        return customerDto;
    }
}
