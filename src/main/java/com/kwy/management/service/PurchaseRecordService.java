package com.kwy.management.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kwy.management.entity.PurchaseRecord;

/**
 * @author haoy
 * @description
 * @date 2023/7/10 12:18
 */
public interface PurchaseRecordService extends IService<PurchaseRecord> {
    boolean purchase(PurchaseRecord purchaseRecord);

    IPage<PurchaseRecord> getPageSimple(Long materialInfoId);

    IPage<PurchaseRecord> getPage(int currentPage, int pageSize, PurchaseRecord record);
}
