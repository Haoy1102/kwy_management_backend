package com.kwy.management.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kwy.management.entity.Customer;
import com.kwy.management.entity.MaterialInfo;
import com.kwy.management.mapper.MatertialInfoMapper;
import com.kwy.management.service.MaterialInfoService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author haoy
 * @description
 * @date 2023/7/10 12:19
 */
@Service
public class MaterialInfoServiceImpl extends ServiceImpl<MatertialInfoMapper, MaterialInfo> implements MaterialInfoService {

    @Autowired
    private MatertialInfoMapper matertialInfoMapper;

    @Override
    public IPage getPage(int currentPage, int pageSize, MaterialInfo materialInfo) {
        LambdaQueryWrapper<MaterialInfo> lqw = new LambdaQueryWrapper<MaterialInfo>();
        lqw.like(Strings.isNotEmpty(materialInfo.getCategory()),
                MaterialInfo::getCategory,materialInfo.getCategory());
        lqw.like(Strings.isNotEmpty(materialInfo.getSupplier()),
                MaterialInfo::getSupplier,materialInfo.getSupplier());
        IPage page = new Page(currentPage, pageSize);
        matertialInfoMapper.selectPage(page, lqw);
        return page;
    }
}
