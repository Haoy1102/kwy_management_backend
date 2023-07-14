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
 * @date 2023/7/10 11:53
 */
@Data
public class GoodsRecord implements Serializable {

    // 1入库 2出库
    public static final int  OPERATE_IN= 1;
    public static final int  OPERATE_OUT= 2;
    public static final int  OPERATE_IN_MANUAL= 3;

    private static final long serialVersionUID = 1L;

    private Long id;

    private Long goodsId;

    private Long materialInfoId;

    private String category;

    private String supplier;

    private Integer operateType;

    private Double operateNumber;

    private Double remainNumber;

    private LocalDate producedDate;

    private String location;

    private Integer status;

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
