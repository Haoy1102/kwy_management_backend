package com.kwy.management.entity.ExcleData;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

/**
 * @author haoy
 * @description
 * @date 2023/7/20 20:39
 */
@Data
@EqualsAndHashCode
public class OrderDemoData {

    private String orderId;

    private String customer;

    private LocalDate createDate;

    private String people;

    private String phone;

    private String address;

    private String note;

    private Double totalAmount;

    private String amountByChinese;
}
