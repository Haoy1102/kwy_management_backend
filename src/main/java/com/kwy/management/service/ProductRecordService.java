package com.kwy.management.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kwy.management.entity.Product;
import com.kwy.management.entity.ProductRecord;

/**
 * @author haoy
 * @description
 * @date 2023/7/14 19:04
 */
public interface ProductRecordService extends IService<ProductRecord> {
    IPage getPage(int currentPage, int pageSize, ProductRecord product);

}
