package com.kwy.management.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.kwy.management.comon.Code;
import com.kwy.management.comon.R;
import com.kwy.management.entity.Customer;
import com.kwy.management.entity.Order;
import com.kwy.management.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author haoy
 * @description
 * @date 2023/7/8 17:17
 */
@Slf4j
@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public R<Boolean> save(@RequestBody Order order){
        return orderService.addOrder(order)?
                R.success("新增成功"):
                R.error("新增失败,请检查数据", Code.SAVE_ERR);
    }

    @DeleteMapping("/{id}")
    public R<Boolean> delete(@PathVariable int id){
        return orderService.removeById(id)?
                R.success("删除成功"):
                R.error("删除失败,失去同步，自动刷新页面",Code.DELETE_ERR);
    }

    @PutMapping
    public R<Boolean> update(@RequestBody Order order){
        return orderService.updateById(order)?
                R.success("修改成功"):
                R.error("修改失败",Code.UPDATE_ERR);
    }

    @GetMapping("/{currentPage}/{pageSize}")
    public R<IPage<Order>> getPage(@PathVariable int currentPage, @PathVariable int pageSize, Order order){
        IPage<Order> page= orderService.getPage(currentPage, pageSize,order);;
        //如果当前页码值大于了总页码值，那么重新执行查询操作，使用最大页码值作为当前页码值
        if( currentPage > page.getPages()){
            page = orderService.getPage((int)page.getPages(), pageSize,order);
        }
        return R.success(page);
    }


























}
