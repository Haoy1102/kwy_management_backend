package com.kwy.management.dto;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author haoy
 * @description
 * @date 2023/7/11 12:40
 */
@Data
public class GoodsOutForm implements Serializable {

    public static final int OPERATE_IN = 1;
    public static final int OPERATE_OUT = 2;

    private static final long serialVersionUID = 1L;

    private Long id;

    private Long materialInfoId;

    private Long goodsId;

    private String category;

    private String supplier;

    private Double number;

    private Double operateNumber;

    private Integer operateType = OPERATE_OUT;

    private String location;

    private LocalDate producedDate;

    private Integer status;

    private String note;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @TableLogic  //逻辑删除 1删除 0正常
    private Integer deleted;
}
