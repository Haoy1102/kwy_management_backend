package com.kwy.management.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kwy.management.entity.Order;
import com.kwy.management.entity.PaymentDetail;

import java.util.List;

/**
 * @author haoy
 * @description
 * @date 2023/7/9 16:03
 */
public interface PaymentDetailService extends IService<PaymentDetail> {

    public List<PaymentDetail> getOrderPaymentDetails(String orderId);

    /**
     * 将item更新到paymentDetail表，并更新order表的total_payment列
     * @param paymentDetail
     * @return
     */
    public Boolean addItem(PaymentDetail paymentDetail);

    public Boolean deleteById(Long id);
}
