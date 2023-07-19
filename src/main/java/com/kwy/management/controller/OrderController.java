package com.kwy.management.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.kwy.management.comon.Code;
import com.kwy.management.comon.R;
import com.kwy.management.dto.OrderAddDto;
import com.kwy.management.entity.Customer;
import com.kwy.management.entity.Order;
import com.kwy.management.entity.OrderDetail;
import com.kwy.management.service.OrderDetailService;
import com.kwy.management.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

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

    @Autowired
    private OrderDetailService orderDetailService;

    @PostMapping
    public R<Boolean> save(@RequestBody OrderAddDto orderAddDto){
        return orderService.addOrder(orderAddDto)?
                R.success("新增成功"):
                R.error("新增失败,请检查数据", Code.SAVE_ERR);
    }

    @DeleteMapping("/{id}")
    public R<Boolean> delete(@PathVariable int id){
        return orderService.removeById(id)?
                R.success("删除成功"):
                R.error("删除失败！数据不同步，自动刷新",Code.DELETE_ERR);
    }

    @PutMapping
    public R<Boolean> update(@RequestBody Order order){
        return orderService.updateById(order)?
                R.success("修改成功"):
                R.error("修改失败！数据不同步，自动刷新",Code.UPDATE_ERR);
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

    @GetMapping("/customers/{customerId}")
    public R<List<Order>> getOrdersOneYearByCustomerId(@PathVariable Long customerId){
        List<Order> orders = orderService.getOrdersOneYearByCustomerId(customerId);
        return R.success(orders);
    }

    @GetMapping("/orderDetails/{orderId}")
    public R<List<OrderDetail>> getOrderDetailsByOrderId(@PathVariable String orderId){
        LambdaQueryWrapper<OrderDetail> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(OrderDetail::getOrderId,orderId);
        List<OrderDetail> list = orderDetailService.list(queryWrapper);
        return R.success(list);
    }
















}
