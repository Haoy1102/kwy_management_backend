package com.kwy.management.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kwy.management.entity.Product;
import com.kwy.management.entity.ProductOverview;
import com.kwy.management.entity.ProductRecord;
import com.kwy.management.mapper.ProductMapper;
import com.kwy.management.mapper.ProductOverviewMapper;
import com.kwy.management.mapper.ProductRecordMapper;
import com.kwy.management.service.ProductService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author haoy
 * @description
 * @date 2023/7/14 19:07
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {

    @Autowired
    private ProductOverviewMapper productOverviewMapper;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ProductRecordMapper productRecordMapper;


    @Override
    public IPage<Product> getPage(int currentPage, int pageSize, Product product) {
        LambdaQueryWrapper<Product> lqw = new LambdaQueryWrapper<>();
        lqw.like(null !=product.getProductId(),
                Product::getProductId, product.getProductId());

        lqw.like(null != product.getBatchId(),
                Product::getBatchId, product.getBatchId());
        lqw.like(Strings.isNotEmpty(product.getLocation()),
                Product::getLocation, product.getLocation());
        lqw.like(Strings.isNotEmpty(product.getProductName()),
                Product::getProductName, product.getProductName());
        lqw.like(Strings.isNotEmpty(product.getProductCode()),
                Product::getProductCode, product.getProductCode());
        IPage page = new Page(currentPage, pageSize);
        productMapper.selectPage(page, lqw);
        return page;
    }

    @Override
    @Transactional
    public boolean deleteProduct(Long id) {
        /*
        1.批次记录添加记录
        2.商品总览中数量减少
        3.删除该条数据
         */

        Product product = productMapper.selectById(id);
//        1.批次记录添加记录

        ProductRecord record = new ProductRecord();
        BeanUtils.copyProperties(product, record);

        record.setOperateType(ProductRecord.OPERATE_DELETE);
        record.setOperateNumber(product.getNumber());
        record.setRemainNumber(0);
        record.setOriginNumber(product.getNumber());
        productRecordMapper.insert(record);

//        2.商品总览中数量减少
        ProductOverview productOverview = productOverviewMapper.selectById(product.getProductId());
        productOverview.setNumber(productOverview.getNumber() - product.getNumber());
        productOverview.setValue(productOverview.getNumber() * productOverview.getPriceDefault());
        productOverviewMapper.updateById(productOverview);

//        3.删除该条数据
        productMapper.deleteById(id);


        return true;
    }

    @Override
    @Transactional
    public boolean update4Product(Product newProduct) {
        /*
        1.先检测数量有没有变化
        2.如果数量有变化 更新总览表
        3.更新记录表
        4.更新Product表
         */
        Product originProduct = productMapper.selectById(newProduct.getId());
        int changeNumber = newProduct.getNumber() - originProduct.getNumber();

//        1.先检测数量有没有变化
        if (changeNumber != 0) {
//            2.如果数量有变化 更新总览表
            ProductOverview productOverview = productOverviewMapper.selectById(newProduct.getProductId());
            productOverview.setNumber(productOverview.getNumber() + changeNumber);
            productOverview.setValue(productOverview.getNumber() * productOverview.getPriceDefault());
            productOverviewMapper.updateById(productOverview);
        }
//        3.更新记录表
        ProductRecord record = new ProductRecord();
        BeanUtils.copyProperties(newProduct, record);

        record.setOperateType(ProductRecord.OPERATE_IN_MANUAL);
        record.setOperateNumber(changeNumber);
        record.setRemainNumber(newProduct.getNumber());
        record.setOriginNumber(originProduct.getNumber());
        productRecordMapper.insert(record);

//        4.更新Product表
        productMapper.updateById(newProduct);

        return true;
    }
}
