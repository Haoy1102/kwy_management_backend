package com.kwy.management.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kwy.management.entity.MaterialInfo;
import com.kwy.management.entity.Product;
import com.kwy.management.entity.ProductOverview;

/**
 * @author haoy
 * @description
 * @date 2023/7/14 19:04
 */
public interface ProductOverviewService extends IService<ProductOverview> {
    IPage getPage(int currentPage, int pageSize, ProductOverview productOverview);

    boolean produce(Product product);
}
