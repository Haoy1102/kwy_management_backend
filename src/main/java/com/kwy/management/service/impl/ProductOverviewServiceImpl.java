package com.kwy.management.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kwy.management.entity.*;
import com.kwy.management.mapper.ProductMapper;
import com.kwy.management.mapper.ProductOverviewMapper;
import com.kwy.management.mapper.ProductRecordMapper;
import com.kwy.management.service.ProductOverviewService;
import com.kwy.management.service.ProductRecordService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author haoy
 * @description
 * @date 2023/7/14 19:06
 */
@Service
public class ProductOverviewServiceImpl extends ServiceImpl<ProductOverviewMapper, ProductOverview> implements ProductOverviewService {

    @Autowired
    private ProductOverviewMapper productOverviewMapper;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ProductRecordMapper productRecordMapper;

    @Override
    public IPage getPage(int currentPage, int pageSize, ProductOverview productOverview) {
        LambdaQueryWrapper<ProductOverview> lqw = new LambdaQueryWrapper<>();
        lqw.like(Strings.isNotEmpty(productOverview.getProductName()),
                ProductOverview::getProductName,productOverview.getProductName());
        lqw.like(Strings.isNotEmpty(productOverview.getProductCode()),
                ProductOverview::getProductCode,productOverview.getProductCode());
        lqw.orderByDesc(ProductOverview::getCreateTime);
        IPage page = new Page(currentPage, pageSize);
        productOverviewMapper.selectPage(page, lqw);
        return page;
    }

    @Override
    @Transactional
    public boolean produce(Product product) {
        /*
        1. product表增加数据
        2. product_overview更新最近生产事件 数量 估值
        3. product_record表添加记录
         */
//        1. product表增加数据
        productMapper.insert(product);
        Long batchId = product.getId();
        product.setBatchId(batchId);
        productMapper.updateById(product);

//        2. product_overview更新最近生产事件 数量 估值
        ProductOverview productOverview = productOverviewMapper.selectById(product.getProductId());
        productOverview.setProducedDateLast(product.getProducedDate());
        productOverview.setNumber(productOverview.getNumber()+product.getNumber());
        productOverview.setValue(productOverview.getNumber()*productOverview.getPriceDefault());
        productOverviewMapper.updateById(productOverview);


//        3. product_record表添加记录
        ProductRecord record = new ProductRecord();
        BeanUtils.copyProperties(product,record);
        record.setOperateType(ProductRecord.OPERATE_PRODUCE);
        record.setOperateNumber(product.getNumber());
        record.setRemainNumber(product.getNumber());
        record.setOriginNumber(0);
        productRecordMapper.insert(record);

        return true;

    }

    @Override
    public boolean updateSelfById(Long productId) {
        ProductOverview productOverview = productOverviewMapper.selectById(productId);
        LambdaQueryWrapper<Product> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Product::getProductId,productId)
                .gt(Product::getNumber, 0);

        List<Product> products = productMapper.selectList(wrapper);

        Integer curNumber=0;
        for(Product product:products){
            curNumber+=product.getNumber();
        }
        productOverview.setNumber(curNumber);
        productOverview.setValue(curNumber*productOverview.getPriceDefault());

        productOverviewMapper.updateById(productOverview);
        return true;
    }

    @Override
    public Double sumColumn(String columnName) {
        QueryWrapper<ProductOverview> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("SUM(" + columnName + ")");
        List<Object> result = productOverviewMapper.selectObjs(queryWrapper);
        return ((BigDecimal) result.get(0)).doubleValue();
    }


}
