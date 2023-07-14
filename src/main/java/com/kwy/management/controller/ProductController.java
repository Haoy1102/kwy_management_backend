package com.kwy.management.controller;

import com.kwy.management.comon.Code;
import com.kwy.management.comon.R;
import com.kwy.management.entity.Order;
import com.kwy.management.entity.Product;
import com.kwy.management.entity.ProductOverview;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author haoy
 * @description
 * @date 2023/7/14 18:56
 */
@Slf4j
@RestController
@RequestMapping("/api/products")
public class ProductController {



//    @PostMapping("/overviews")
//    public R<Boolean> save(@RequestBody ProductOverview productOverview){
//        return orderService.addOrder(order)?
//                R.success("新增成功"):
//                R.error("新增失败,请检查数据", Code.SAVE_ERR);
//    }
}
