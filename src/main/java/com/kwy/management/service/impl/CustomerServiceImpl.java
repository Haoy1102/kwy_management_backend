package com.kwy.management.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kwy.management.dto.CustomerDto;
import com.kwy.management.entity.Customer;
import com.kwy.management.entity.Order;
import com.kwy.management.mapper.CustomerMapper;
import com.kwy.management.mapper.OrderDetailMapper;
import com.kwy.management.mapper.OrderMapper;
import com.kwy.management.service.CustomerService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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

    @Autowired
    private OrderDetailMapper orderDetailMapper;

    @Override
    public IPage getPage(int currentPage, int pageSize, Customer customer) {
        LambdaQueryWrapper<Customer> lqw = new LambdaQueryWrapper<Customer>();
        lqw.like(Strings.isNotEmpty(customer.getCustomer()),
                Customer::getCustomer, customer.getCustomer());
        lqw.like(Strings.isNotEmpty(customer.getAddress()),
                Customer::getAddress, customer.getAddress());
        lqw.like(Strings.isNotEmpty(customer.getPeople()),
                Customer::getPeople, customer.getPeople());
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
            1. 本月总成交额     本月订单(非作废，非返场)的总成交额
            2. 本月已出货额    本月订单(非作废，非返场)的出货额度
            3. 总支付金额       本月订单(非作废，非返场)的总已回金额
            4. 本月欠款金额    本月订单(非作废，非返场)总交付额-已回金额
         */
        CustomerDto customerDto = new CustomerDto();
        Customer customer = customerMapper.selectById(id);
        BeanUtils.copyProperties(customer, customerDto);

//      获取本月的起始日期和结束日期
        LocalDate currentDate = LocalDate.now();
        LocalDate firstDayOfMonth = currentDate.withDayOfMonth(1);
        LocalDate lastDayOfMonth = currentDate.withDayOfMonth(currentDate.lengthOfMonth());

//      设置查询条件
        LambdaQueryWrapper<Order> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Order::getCustomerId, id)
                .ge(Order::getCreateTime, LocalDateTime.of(firstDayOfMonth, LocalTime.MIN))
                .le(Order::getCreateTime, LocalDateTime.of(lastDayOfMonth, LocalTime.MAX));

        List<Order> orders = orderMapper.selectList(lqw);


//        1. 本月总成交额     本月订单(非作废，非返场)的总成交额
        Integer[] targetStatus = {1, 2, 3, 4, 5}; // 目标订单状态
        double totalAmount = 0.0;
        for (Order order : orders) {
            if (Arrays.asList(targetStatus).contains(order.getStatus())) {
                totalAmount += order.getAmount();
            }
        }
        customerDto.setTotalAmount(totalAmount);


//      2.本月已出货额    本月订单(非作废，非返场)的出货额度
        double totalAmountDelivered = 0.0;
        for (Order order : orders) {
            if (Arrays.asList(targetStatus).contains(order.getStatus())) {
                totalAmountDelivered += order.getTotalDelivered();
            }
        }
        customerDto.setTotalAmountDelivered(totalAmountDelivered);

//      3.总支付金额       本月订单(非作废，非返场)的总已回金额
        double totalPayment = 0.0;
        for (Order order : orders) {
            if (Arrays.asList(targetStatus).contains(order.getStatus())) {
                totalPayment += order.getTotalPayment();
            }
        }
        customerDto.setTotalAmountPayment(totalPayment);

//      4.本月欠款金额    本月订单(非作废，非返场)总交付额-已回金额
        customerDto.setTotalAmountDebt(totalAmountDelivered-totalPayment);


        return customerDto;
    }
}
