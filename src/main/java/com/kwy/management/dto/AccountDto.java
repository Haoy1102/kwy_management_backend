package com.kwy.management.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author haoy
 * @description
 * @date 2023/7/12 12:51
 */
@Data
public class AccountDto implements Serializable {

//    近3月订单总额
    private Double totalAmount;

//    未完成订单金额
    private Double totalAmountCurrent;

//    已出货金额
    private Double totalAmountDelivered;

//    未出货金额
    private Double totalAmountNoDelivered;

//    待入账总金额
    private Double totalAmountDebt;

//    原材料总资产
    private Double totalAssetsMaterial;

//    产品总资产
    private Double totalAssetsProduct;

//    总入账金额
    private Double totalAmoutPayment;

//    总出账金额
    private Double totalAmoutExpend;



}
