package com.kwy.management.entity.ExcleData;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

/**
 * @author haoy
 * @description
 * @date 2023/7/21 12:38
 */
@Data
@EqualsAndHashCode
public class CheckFormPaymentDemo {
    private String orderId;
    private LocalDate payDate;
    private Double payAmount;
    private String payNote;
}
