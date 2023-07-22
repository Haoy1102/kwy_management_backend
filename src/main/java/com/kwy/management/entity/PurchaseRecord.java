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
 * @date 2023/7/10 12:04
 */
@Data
public class PurchaseRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Long goodsId;

    private Long categoryId;

    private Long materialInfoId;
//    private Long goodsId;

    private String category;

    private String supplier;

    private Integer number;

    private Double price;

    private Double totalAmount;

    private String location;

    private LocalDate producedDate;

    private Integer status;

    private LocalDate createDate;

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
