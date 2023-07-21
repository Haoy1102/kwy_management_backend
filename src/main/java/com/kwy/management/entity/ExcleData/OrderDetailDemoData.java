package com.kwy.management.entity.ExcleData;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author haoy
 * @description
 * @date 2023/7/20 21:34
 */
@Data
@EqualsAndHashCode
public class OrderDetailDemoData {
    private String productName;

    private String productCode;

    private Integer packageNumber;

    private String unit;

    private Integer number;

    private Double price;

    private Double amount;
}
