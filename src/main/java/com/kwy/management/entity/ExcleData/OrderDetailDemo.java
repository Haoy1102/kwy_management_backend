package com.kwy.management.entity.ExcleData;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author haoy
 * @description
 * @date 2023/7/22 15:49
 */
@Data
@EqualsAndHashCode
public class OrderDetailDemo {

    @ExcelProperty("订单号")
    private String orderId;

    @ExcelProperty("产品名称")
    private String productName;

    @ExcelProperty("产品编号")
    private String productCode;

    @ExcelProperty("件数")
    private Integer packageNumber;

    @ExcelProperty("单位")
    private String unit;

    @ExcelProperty("数量")
    private Integer number;

    @ExcelProperty("单价")
    private Double price;

    @ExcelProperty("金额")
    private Double amount;

    @ExcelProperty("交付状态")
    private String isDeliveredLabel;

    @ExcelProperty("交付日期")
    private LocalDate deliveredDate;

    @ExcelProperty("备注")
    private String note;

    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @ExcelProperty("修改时间")
    private LocalDateTime updateTime;

    @ExcelProperty("创建人")
    private String createUser;

    @ExcelProperty("修改人")
    private String updateUser;
}
