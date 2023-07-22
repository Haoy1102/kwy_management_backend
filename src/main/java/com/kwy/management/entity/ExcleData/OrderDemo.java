package com.kwy.management.entity.ExcleData;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.Version;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author haoy
 * @description
 * @date 2023/7/22 15:45
 */
@Data
@EqualsAndHashCode
public class OrderDemo {

    @ExcelProperty("订单号")
    private String orderId;

    @ExcelProperty("客户")
    private String customer;

    @ExcelProperty("联系人")
    private String people;

    @ExcelProperty("联系电话")
    private String phone;

    @ExcelProperty("地址")
    private String address;

    @ExcelProperty("订单日期")
    private LocalDate createDate;

    @ExcelProperty("订单状态")
    private String statusLabel;

    @ExcelProperty("订单金额")
    private Double amount;

    @ExcelProperty("交付进度")
    private Integer deliveryProgress;

    @ExcelProperty("已回金额")
    private Double totalPayment;

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
