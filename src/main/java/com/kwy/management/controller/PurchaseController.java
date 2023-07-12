package com.kwy.management.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.kwy.management.comon.Code;
import com.kwy.management.comon.R;
import com.kwy.management.entity.MaterialInfo;
import com.kwy.management.entity.PurchaseRecord;
import com.kwy.management.service.PurchaseRecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author haoy
 * @description
 * @date 2023/7/10 19:34
 */
@Slf4j
@RestController
@RequestMapping("/api/purchase")
public class PurchaseController {

    @Autowired
    private PurchaseRecordService purchaseRecordService;


    @PostMapping
    public R<Boolean> savePurchase(@RequestBody PurchaseRecord purchaseRecord){
        return purchaseRecordService.purchase(purchaseRecord)?
                R.success("添加成功"):
                R.error("添加失败", Code.SAVE_ERR);
    }

    @DeleteMapping("{id}")
    public R<Boolean> delete(@PathVariable Long id){
        return purchaseRecordService.removeById(id)?
                R.success("删除成功"):
                R.error("删除失败！数据不同步，自动刷新", Code.DELETE_ERR);
    }

    @PutMapping
    public R<Boolean> update(@RequestBody PurchaseRecord purchaseRecord){
        return purchaseRecordService.updateById(purchaseRecord)?
                R.success("修改成功"):
                R.error("修改失败！数据不同步，自动刷新", Code.DELETE_ERR);
    }

    @GetMapping("/{materialInfoId}")
    public R<IPage<PurchaseRecord>> getPageSimple(@PathVariable Long materialInfoId){
        IPage<PurchaseRecord> page= purchaseRecordService.getPageSimple(materialInfoId);
        return R.success(page);
    }

    @GetMapping("/{currentPage}/{pageSize}")
    public R<IPage<PurchaseRecord>> getPage(@PathVariable int currentPage, @PathVariable int pageSize,PurchaseRecord record){
        IPage<PurchaseRecord> page= purchaseRecordService.getPage(currentPage,pageSize,record);
        return R.success(page);
    }
}
