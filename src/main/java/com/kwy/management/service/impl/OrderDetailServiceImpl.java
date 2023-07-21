package com.kwy.management.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kwy.management.dto.OrderDetailsBatchDeliverDto;
import com.kwy.management.dto.OrderDetailsDeliverDto;
import com.kwy.management.entity.OrderDetail;
import com.kwy.management.mapper.OrderDetailMapper;
import com.kwy.management.service.OrderDetailService;
import com.kwy.management.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

/**
 * @author haoy
 * @description
 * @date 2023/7/14 19:05
 */
@Service
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetail> implements OrderDetailService {

    @Autowired
    private OrderDetailMapper orderDetailMapper;

    @Override
    public boolean deliver(OrderDetail orderDetail) {
        orderDetail.setIsDelivered(OrderDetail.DELIVERED);
        orderDetail.setDeliveredDate(LocalDate.now());
        orderDetailMapper.updateById(orderDetail);
        return true;
    }

    @Override
    public boolean deliverBatch(OrderDetailsBatchDeliverDto batchDeliverDto) {
        return false;
    }

    @Override
    @Transactional
    public boolean updateDetail(OrderDetail detail) {
        /*
        1. 更新orderDetail表
        2. Order表内容重新计算
         */
//        1. 更新orderDetail表
        orderDetailMapper.updateById(detail);

        return false;
    }
}
