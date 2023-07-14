package com.kwy.management.entity.ExcleData;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author haoy
 * @description
 * @date 2023/7/13 13:44
 */
@Data
@EqualsAndHashCode
public class DemoData {
    private String string;
    private Date date;
    private Double doubleData;
    private Double number;
    private Double price;
}
