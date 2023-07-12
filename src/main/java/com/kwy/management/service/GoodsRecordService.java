package com.kwy.management.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kwy.management.entity.GoodsRecord;
import com.kwy.management.entity.PurchaseRecord;

/**
 * @author haoy
 * @description
 * @date 2023/7/10 12:18
 */
public interface GoodsRecordService extends IService<GoodsRecord> {
    IPage<GoodsRecord> getPage(int currentPage, int pageSize, GoodsRecord record);

    IPage<GoodsRecord> getPageSimple(Long goodsId);
}
