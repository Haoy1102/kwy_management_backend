package com.kwy.management.dto;

import com.kwy.management.entity.Order;
import com.kwy.management.entity.OrderDetail;
import com.kwy.management.entity.Product;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author haoy
 * @description
 * @date 2023/7/20 11:54
 */
@Data
public class OrderDetailsBatchDeliverDto implements Serializable {

    private List<OrderDetail> orderDetails;
}
