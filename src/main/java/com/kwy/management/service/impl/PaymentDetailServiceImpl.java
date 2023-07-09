package com.kwy.management.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kwy.management.entity.Order;
import com.kwy.management.entity.PaymentDetail;
import com.kwy.management.mapper.OrderMapper;
import com.kwy.management.mapper.PaymentDetailMapper;
import com.kwy.management.service.PaymentDetailService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Queue;

/**
 * @author haoy
 * @description
 * @date 2023/7/9 16:04
 */
@Service
public class PaymentDetailServiceImpl extends ServiceImpl<PaymentDetailMapper, PaymentDetail> implements PaymentDetailService {

    @Autowired
    private PaymentDetailMapper detailMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public List<PaymentDetail> getOrderPaymentDetails(String orderId) {
        LambdaQueryWrapper<PaymentDetail> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Strings.isNotBlank(orderId),
                PaymentDetail::getOrderId,orderId);
        return detailMapper.selectList(lqw);
    }

    /**
     * 将item添加到paymentDetail表，并更新order表的total_payment列
     * @param paymentDetail
     * @return
     */
    @Override
    @Transactional
    public Boolean addItem(PaymentDetail paymentDetail) {
        detailMapper.insert(paymentDetail);
        LambdaQueryWrapper<Order> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Order::getOrderId,paymentDetail.getOrderId());
        Order order=orderMapper.selectOne(lqw);
        order.setTotalPayment(order.getTotalPayment() + paymentDetail.getAmount());
        orderMapper.updateById(order);
        return true;
    }

    @Override
    @Transactional
    public Boolean deleteById(Long id) {
        PaymentDetail detail = detailMapper.selectById(id);
        detailMapper.deleteById(id);
        LambdaQueryWrapper<Order> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Order::getOrderId,detail.getOrderId());
        Order order=orderMapper.selectOne(lqw);
        order.setTotalPayment(order.getTotalPayment()-detail.getAmount());
        orderMapper.updateById(order);
        return true;
    }
}
