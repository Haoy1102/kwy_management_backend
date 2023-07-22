package com.kwy.management.dto;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.Version;
import com.kwy.management.entity.GoodsRecord;
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

    private static final long serialVersionUID = 1L;

    private Long goodsId;

    private Integer operateNumber;

    private Integer operateType = GoodsRecord.OPERATE_OUT;

    private String note;

}
