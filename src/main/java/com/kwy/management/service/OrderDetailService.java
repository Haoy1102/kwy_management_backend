package com.kwy.management.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kwy.management.dto.OrderDetailsBatchDeliverDto;
import com.kwy.management.dto.OrderDetailsDeliverDto;
import com.kwy.management.entity.OrderDetail;

/**
 * @author haoy
 * @description
 * @date 2023/7/14 19:02
 */
public interface OrderDetailService extends IService<OrderDetail> {
    boolean deliver(OrderDetail orderDetail);

    boolean deliverBatch(OrderDetailsBatchDeliverDto batchDeliverDto);

    boolean updateDetail(OrderDetail detail);
}
