package com.kwy.management.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kwy.management.dto.OrderDetailsDeliverDto;
import com.kwy.management.entity.OrderDetail;
import com.kwy.management.entity.Product;

/**
 * @author haoy
 * @description
 * @date 2023/7/14 19:03
 */
public interface ProductService extends IService<Product> {
    IPage<Product> getPage(int currentPage, int pageSize, Product product);

    boolean deleteProduct(Long id);

    boolean update4Product(Product product);

    boolean deliver(OrderDetailsDeliverDto orderDetailsDeliverDto);

    boolean deliverAuto(OrderDetail orderDetail);
}
