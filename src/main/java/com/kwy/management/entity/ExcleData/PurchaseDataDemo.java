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
 * @date 2023/7/22 15:00
 */
@Data
@EqualsAndHashCode
public class PurchaseDataDemo {

    @ExcelProperty("采购记录ID")
    private Long id;

    @ExcelProperty("货号")
    private Long goodsId;

    @ExcelProperty("品类")
    private String category;

    @ExcelProperty("货源")
    private String supplier;

    @ExcelProperty("数量")
    private Integer number;

    @ExcelProperty("单价")
    private Double price;

    @ExcelProperty("总金额")
    private Double totalAmount;

    @ExcelProperty("仓储")
    private String location;

    @ExcelProperty("生产日期")
    private LocalDate producedDate;

    @ExcelProperty("状态")
    private String statusLabel;

    @ExcelProperty("订单日期")
    private LocalDate createDate;

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
