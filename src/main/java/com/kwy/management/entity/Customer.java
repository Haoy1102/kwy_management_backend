package com.kwy.management.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author haoy
 * @description
 * @date 2023/7/7 11:04
 */
@Data
public class Customer implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    private String customer;

    private String people;

    private String phone;

    private String phoneStandby;

    private String address;

    private String note;

    @TableLogic  //逻辑删除 1删除 0正常
    private Integer deleted;
}
