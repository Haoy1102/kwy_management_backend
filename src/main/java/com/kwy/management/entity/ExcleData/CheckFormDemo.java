package com.kwy.management.entity.ExcleData;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

/**
 * @author haoy
 * @description
 * @date 2023/7/21 12:26
 */
@Data
@EqualsAndHashCode
public class CheckFormDemo {

    private String customer;

    private LocalDate startDate;

    private LocalDate endDate;

    private Double totalAmount;

    private String amountByChinese;

    private Double totalPayment;

    private Double totalDebt;

}
