package com.kwy.management.dto;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.Version;
import com.kwy.management.entity.OrderDetail;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author haoy
 * @description
 * @date 2023/7/19 19:18
 */
@Data
public class OrderAddDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String orderId;

    private Long customerId;

    private String customer;

    private String people;

    private String content;

    private String phone;

    private String address;

    private LocalDate createDate;

    private Integer status;

    private Double amount;

    private Integer deliveryProgress;

    private Double totalPayment;

    private String note;

    private List<OrderDetail> orderDetails;

}
