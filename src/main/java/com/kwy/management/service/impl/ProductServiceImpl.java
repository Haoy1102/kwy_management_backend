package com.kwy.management.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kwy.management.dto.OrderDetailsDeliverDto;
import com.kwy.management.entity.*;
import com.kwy.management.mapper.OrderMapper;
import com.kwy.management.mapper.ProductMapper;
import com.kwy.management.mapper.ProductOverviewMapper;
import com.kwy.management.mapper.ProductRecordMapper;
import com.kwy.management.service.ProductService;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

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

    @Autowired
    private OrderMapper orderMapper;


    @Override
    public IPage<Product> getPage(int currentPage, int pageSize, Product product) {
        LambdaQueryWrapper<Product> lqw = new LambdaQueryWrapper<>();
        lqw.like(null != product.getProductId(),
                Product::getProductId, product.getProductId());

        lqw.like(null != product.getBatchId(),
                Product::getBatchId, product.getBatchId());
        lqw.like(Strings.isNotEmpty(product.getLocation()),
                Product::getLocation, product.getLocation());
        lqw.like(Strings.isNotEmpty(product.getProductName()),
                Product::getProductName, product.getProductName());
        lqw.like(Strings.isNotEmpty(product.getProductCode()),
                Product::getProductCode, product.getProductCode());
        lqw.orderByDesc(Product::getCreateTime);
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

    @Override
    @Transactional
    public boolean deliver(OrderDetailsDeliverDto orderDetailsDeliverDto) {
        /*
        1.Product表出货
            1.1.产品表具体批次的数量计算
            1.2.批次记录需要加上 其中有去向客户
                1.2.1产品表更新
         */
        OrderDetail orderDetail = orderDetailsDeliverDto.getOrderDetail();
        String orderId = orderDetail.getOrderId();
        List<Product> products = orderDetailsDeliverDto.getProducts();
        List<Product> selectedProducts = new ArrayList<>();

        // 按照producedDate生产日期进行升序排序
        Collections.sort(products, Comparator.comparing(Product::getProducedDate));

//        1.1.产品表具体批次的数量计算
        Integer targetNumber = orderDetail.getNumber();
        Integer curNumber = 0;

        for (Product product : products) {
            // 已经找到足够数量的货物，停止遍历
            if (curNumber >= targetNumber)
                break;

            Integer productNumber = product.getNumber();

            if (productNumber <= (targetNumber - curNumber)) {
                // 将整个货物添加到selectedProducts列表中
                selectedProducts.add(product);
                curNumber += productNumber;
            } else {
                // 当前货物数量大于剩余需要的数量，拆分货物
                Integer remainingNumber = targetNumber - curNumber;

                Product productOut = new Product();
                BeanUtils.copyProperties(product, productOut);
                productOut.setNumber(remainingNumber);
                selectedProducts.add(productOut);

                curNumber += remainingNumber;

                break; // 停止遍历，已找到足够数量的货物
            }
        }
        //没找到足够的货物 发货失败
        if (curNumber < targetNumber)
            return false;

//        1.2.批次记录需要加上 其中有去向客户
        for (Product selectProduct : selectedProducts) {
            ProductRecord record = new ProductRecord();
            BeanUtils.copyProperties(selectProduct, record);


            for (Product originProduct : products) {
                if (originProduct.getId().equals(selectProduct.getId())){

                    record.setOperateType(ProductRecord.OPERATE_DELIVER);
                    record.setOperateNumber(selectProduct.getNumber());
                    record.setRemainNumber(originProduct.getNumber()-selectProduct.getNumber());
                    record.setOriginNumber(originProduct.getNumber());

                    //去向客户等信息
                    record.setOrderId(orderId);

                    //查找客户名称
                    LambdaQueryWrapper<Order> wrapper = new LambdaQueryWrapper<>();
                    wrapper.eq(Order::getOrderId,orderId);
                    Order order = orderMapper.selectOne(wrapper);
                    String customer = order.getCustomer();

                    record.setCustomer(customer);
                    record.setDeliveredDate(LocalDate.now());

                    productRecordMapper.insert(record);
//          1.2.1产品表更新
                    originProduct.setNumber(originProduct.getNumber()-selectProduct.getNumber());
                    productMapper.updateById(originProduct);
                }
            }
        }
        return true;
    }
}
