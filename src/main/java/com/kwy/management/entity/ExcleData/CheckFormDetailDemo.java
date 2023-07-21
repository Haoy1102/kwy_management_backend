package com.kwy.management.entity.ExcleData;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

/**
 * @author haoy
 * @description
 * @date 2023/7/21 12:33
 */
@Data
@EqualsAndHashCode
public class CheckFormDetailDemo {

    private LocalDate orderDate;

    private LocalDate deliveredDate;

    private String productName;

    private String productCode;

    private String unit;

    private Integer number;

    private Double price;

    private Double amount;

    private String note;
}
