package com.kwy.management.controller;

import com.kwy.management.comon.Code;
import com.kwy.management.comon.R;
import com.kwy.management.entity.PaymentDetail;
import com.kwy.management.service.PaymentDetailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author haoy
 * @description
 * @date 2023/7/9 16:05
 */
@Slf4j
@RestController
@RequestMapping("/api/paymentDetails")
public class PaymentDetailController {

    @Autowired
    private PaymentDetailService paymentDetailService;

    @PostMapping
    public R<Boolean> save(@RequestBody PaymentDetail paymentDetail){
        return paymentDetailService.addItem(paymentDetail)?
                R.success("添加成功"):
                R.error("添加失败", Code.SAVE_ERR);
    }

    @DeleteMapping("/{id}")
    public R<Boolean> delete(@PathVariable Long id){
        return paymentDetailService.deleteById(id)?
                R.success("删除成功"):
                R.error("删除失败",Code.DELETE_ERR);
    }

    @PutMapping
    public R<Boolean> update(@RequestBody PaymentDetail paymentDetail){
        return paymentDetailService.updateById(paymentDetail)?
                R.success("修改成功"):
                R.error("修改失败",Code.UPDATE_ERR);
    }

    @GetMapping("/{orderId}")
    public R<List<PaymentDetail>> getOrderPaymentDetails(@PathVariable String orderId){
        System.out.println(orderId);
        return R.success(paymentDetailService.getOrderPaymentDetails(orderId));
    }














}
