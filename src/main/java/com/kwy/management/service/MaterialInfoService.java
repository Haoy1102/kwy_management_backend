package com.kwy.management.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kwy.management.entity.MaterialInfo;

/**
 * @author haoy
 * @description
 * @date 2023/7/10 12:16
 */
public interface MaterialInfoService extends IService<MaterialInfo> {


    public IPage getPage(int currentPage, int pageSize, MaterialInfo materialInfo);
}
