package com.kwy.management.dto;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.Version;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author haoy
 * @description
 * @date 2023/7/11 18:29
 */
@Data
public class CustomerDto  implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    private String customer;

    private String people;

    private String phone;

    private String phoneStandby;

    private String address;

    private String note;

//    private Double totalAmountPerYear;
//
//    private Double totalAmountCurrent;
//
//    private Double totalAmountDelivered;
//
//    private Double totalAmountDebt4Completed;
//
//    private Double totalAmountPayment;
//
//    private Double totalAmountDebt;

    private Double totalAmount;

    private Double totalAmountDelivered;

    private Double totalAmountPayment;

    private Double totalAmountDebt;


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
