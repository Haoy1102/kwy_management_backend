package com.kwy.management.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.Version;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author haoy
 * @description
 * @date 2023/7/8 15:14
 */
@Data
public class Order implements Serializable {

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

    private Double totalDelivered;

    private Integer deliveryProgress;

    private Double totalPayment;

    private String note;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @TableField(fill = FieldFill.INSERT)
    private String createUser;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updateUser;

    @Version
    private Integer version;

    @TableLogic  //逻辑删除 1删除 0正常
    private Integer deleted;
}
