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
 * @date 2023/7/14 18:36
 */
@Data
public class ProductRecord implements Serializable {
    private static final long serialVersionUID = 1L;

    //1生产 2出货 3手动调整 4删除
    public static final Integer OPERATE_PRODUCE = 1;
    public static final Integer OPERATE_DELIVER = 2;
    public static final Integer OPERATE_MANUAL = 3;
    public static final Integer OPERATE_DELETE = 4;
    private Long id;

    private Long batchId;

    private Long productId;

    private String orderId;

    private String productName;

    private String productCode;

    private String unit;

    private Integer operateType;

    private Integer operateNumber;

    private Integer remainNumber;

    private Integer originNumber;

    private LocalDate producedDate;

    private Integer status;

    private String customer;

    private LocalDate deliveredDate;

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
