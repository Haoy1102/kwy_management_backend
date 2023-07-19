package com.kwy.management.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.kwy.management.comon.Code;
import com.kwy.management.comon.R;
import com.kwy.management.entity.*;
import com.kwy.management.service.ProductOverviewService;
import com.kwy.management.service.ProductRecordService;
import com.kwy.management.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @Autowired
    private ProductRecordService productRecordService;

    @DeleteMapping("/{id}")
    public R<Boolean> delete4Product(@PathVariable Long id) {
        return productService.deleteProduct(id) ?
                R.success("删除成功") :
                R.error("删除失败！数据不同步，自动刷新", Code.DELETE_ERR);
    }

    @PutMapping
    public R<Boolean> update4Product(@RequestBody Product product) {
        return productService.update4Product(product) ?
                R.success("修改成功") :
                R.error("修改失败！数据不同步，自动刷新", Code.UPDATE_ERR);
    }

    @GetMapping("/{currentPage}/{pageSize}")
    public R<IPage<Product>> getPage4Product(@PathVariable int currentPage,
                                             @PathVariable int pageSize,
                                             Product product) {
        IPage<Product> page = productService.getPage(currentPage, pageSize, product);
        //如果当前页码值大于了总页码值，那么重新执行查询操作，使用最大页码值作为当前页码值
        if (currentPage > page.getPages()) {
            page = productService.getPage((int) page.getPages(), pageSize, product);
        }
        return R.success(page);
    }


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

    @GetMapping("/overviews")
    public R<List<ProductOverview>> getAll4Overview() {
        List<ProductOverview> list = productOverviewService.list();
        return !list.isEmpty()?
                R.success(list):
                R.error("请先新增产品数据",Code.GET_ERR);
    }

    @PostMapping("/overviews/produce")
    public R<Boolean> produce(@RequestBody Product product) {
        return productOverviewService.produce(product) ?
                R.success("新增成功") :
                R.error("新增失败,请检查数据", Code.SAVE_ERR);
    }


    @GetMapping("/records/{currentPage}/{pageSize}")
    public R<IPage<ProductRecord>> getPage4ProductRecord(@PathVariable int currentPage,
                                             @PathVariable int pageSize,
                                             ProductRecord record) {
        IPage<ProductRecord> page = productRecordService.getPage(currentPage, pageSize, record);
        //如果当前页码值大于了总页码值，那么重新执行查询操作，使用最大页码值作为当前页码值
        if (currentPage > page.getPages()) {
            page = productRecordService.getPage((int) page.getPages(), pageSize, record);
        }
        return R.success(page);
    }

    @PutMapping("/records")
    public R<Boolean> update4ProductRecord(@RequestBody ProductRecord record) {
        return productRecordService.updateById(record) ?
                R.success("修改成功") :
                R.error("修改失败！数据不同步，自动刷新", Code.UPDATE_ERR);
    }

    @GetMapping("/{productId}")
    public R<List<Product>> getProductsByProductId(@PathVariable Long productId) {
        LambdaQueryWrapper<Product> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Product::getProductId,productId);
        List<Product> list = productService.list(queryWrapper);
        return R.success(list);
    }
}
