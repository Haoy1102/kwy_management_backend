package com.kwy.management.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kwy.management.entity.MaterialOverview;

/**
 * @author haoy
 * @description
 * @date 2023/7/10 12:17
 */
public interface MaterialOverviewService extends IService<MaterialOverview> {
    IPage<MaterialOverview> getPage(int currentPage, int pageSize, MaterialOverview overview);

    Double sumColumn(String columnName);
}
