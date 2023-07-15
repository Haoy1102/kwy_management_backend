package com.kwy.management.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.kwy.management.comon.Code;
import com.kwy.management.comon.R;
import com.kwy.management.entity.MaterialInfo;
import com.kwy.management.entity.Order;
import com.kwy.management.entity.Product;
import com.kwy.management.entity.ProductOverview;
import com.kwy.management.service.ProductOverviewService;
import com.kwy.management.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author haoy
 * @description
 * @date 2023/7/14 18:56
 */
@Slf4j
@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductOverviewService productOverviewService;

    @Autowired
    private ProductService productService;


    @PostMapping("/overviews")
    public R<Boolean> save(@RequestBody ProductOverview productOverview) {
        return productOverviewService.save(productOverview) ?
                R.success("新增成功") :
                R.error("新增失败,请检查数据", Code.SAVE_ERR);
    }

    @DeleteMapping("/overviews/{id}")
    public R<Boolean> delete(@PathVariable Long id) {
        return productOverviewService.removeById(id) ?
                R.success("删除成功") :
                R.error("删除失败！数据不同步，自动刷新", Code.DELETE_ERR);
    }

    @PutMapping("/overviews")
    public R<Boolean> update(@RequestBody ProductOverview productOverview) {
        return productOverviewService.updateById(productOverview) ?
                R.success("修改成功") :
                R.error("修改失败！数据不同步，自动刷新", Code.UPDATE_ERR);
    }

    @GetMapping("/overviews/{currentPage}/{pageSize}")
    public R<IPage<ProductOverview>> getPage(@PathVariable int currentPage,
                                          @PathVariable int pageSize,
                                          ProductOverview productOverview) {
        IPage<ProductOverview> page = productOverviewService.getPage(currentPage, pageSize, productOverview);
        //如果当前页码值大于了总页码值，那么重新执行查询操作，使用最大页码值作为当前页码值
        if (currentPage > page.getPages()) {
            page = productOverviewService.getPage((int) page.getPages(), pageSize, productOverview);
        }
        return R.success(page);
    }

    @PostMapping("/overviews/produce")
    public R<Boolean> produce(@RequestBody Product product) {
        return productOverviewService.produce(product) ?
                R.success("新增成功") :
                R.error("新增失败,请检查数据", Code.SAVE_ERR);
    }


}
