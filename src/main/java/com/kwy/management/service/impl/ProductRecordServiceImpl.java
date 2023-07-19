package com.kwy.management.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kwy.management.entity.Product;
import com.kwy.management.entity.ProductRecord;
import com.kwy.management.mapper.ProductRecordMapper;
import com.kwy.management.service.ProductRecordService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author haoy
 * @description
 * @date 2023/7/14 19:06
 */
@Service
public class ProductRecordServiceImpl extends ServiceImpl<ProductRecordMapper, ProductRecord> implements ProductRecordService {

    @Autowired
    private ProductRecordMapper productRecordMapper;

    @Override
    public IPage getPage(int currentPage, int pageSize, ProductRecord record) {
        LambdaQueryWrapper<ProductRecord> lqw = new LambdaQueryWrapper<>();
        lqw.like(null != record.getId(),
                ProductRecord::getId, record.getId());
        lqw.like(null != record.getBatchId(),
                ProductRecord::getBatchId, record.getBatchId());
        lqw.like(Strings.isNotEmpty(record.getProductName()),
                ProductRecord::getProductName, record.getProductName());
        lqw.like(Strings.isNotEmpty(record.getProductCode()),
                ProductRecord::getProductCode, record.getProductCode());
        lqw.like(Strings.isNotEmpty(record.getCustomer()),
                ProductRecord::getCustomer, record.getCustomer());
        lqw.like(Strings.isNotEmpty(record.getOrderId()),
                ProductRecord::getOrderId, record.getOrderId());
        IPage page = new Page(currentPage, pageSize);
        productRecordMapper.selectPage(page, lqw);
        return page;
    }
}
