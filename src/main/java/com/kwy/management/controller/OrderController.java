package com.kwy.management.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.util.MapUtils;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.fill.FillConfig;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.kwy.management.comon.Code;
import com.kwy.management.comon.R;
import com.kwy.management.dto.OrderAddDto;
import com.kwy.management.dto.OrderDetailsBatchDeliverDto;
import com.kwy.management.dto.OrderDetailsDeliverDto;
import com.kwy.management.entity.ExcleData.DemoData;
import com.kwy.management.entity.ExcleData.FillData;
import com.kwy.management.entity.ExcleData.OrderDemoData;
import com.kwy.management.entity.ExcleData.OrderDetailDemoData;
import com.kwy.management.entity.Order;
import com.kwy.management.entity.OrderDetail;
import com.kwy.management.service.OrderDetailService;
import com.kwy.management.service.OrderService;
import com.kwy.management.service.ProductOverviewService;
import com.kwy.management.service.ProductService;
import com.kwy.management.utils.NumberConverterUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Map;

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

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductOverviewService productOverviewService;

    @PostMapping
    public R<Boolean> save(@RequestBody OrderAddDto orderAddDto) {
        return orderService.addOrder(orderAddDto) ?
                R.success("新增成功") :
                R.error("新增失败,请检查数据", Code.SAVE_ERR);
    }

    @DeleteMapping("/{id}")
    public R<Boolean> delete(@PathVariable int id) {
        return orderService.removeById(id) ?
                R.success("删除成功") :
                R.error("删除失败！数据不同步，自动刷新", Code.DELETE_ERR);
    }

    @PutMapping
    public R<Boolean> update(@RequestBody Order order) {
        return orderService.updateById(order) ?
                R.success("修改成功") :
                R.error("修改失败！数据不同步，自动刷新", Code.UPDATE_ERR);
    }

    @GetMapping("/{currentPage}/{pageSize}")
    public R<IPage<Order>> getPage(@PathVariable int currentPage, @PathVariable int pageSize, Order order) {
        IPage<Order> page = orderService.getPage(currentPage, pageSize, order);
        ;
        //如果当前页码值大于了总页码值，那么重新执行查询操作，使用最大页码值作为当前页码值
        if (currentPage > page.getPages()) {
            page = orderService.getPage((int) page.getPages(), pageSize, order);
        }
        return R.success(page);
    }

    @GetMapping("/customers/{customerId}")
    public R<List<Order>> getOrdersOneYearByCustomerId(@PathVariable Long customerId) {
        List<Order> orders = orderService.getOrdersOneYearByCustomerId(customerId);
        return R.success(orders);
    }

    @GetMapping("/orderDetails/{orderId}")
    public R<List<OrderDetail>> getOrderDetailsByOrderId(@PathVariable String orderId) {
        LambdaQueryWrapper<OrderDetail> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(OrderDetail::getOrderId, orderId);
        List<OrderDetail> list = orderDetailService.list(queryWrapper);
        return R.success(list);
    }

    @PostMapping("/orderDetails/deliver")
    @Transactional
    public R<Boolean> orderDetailsDeliver(@RequestBody OrderDetailsDeliverDto deliverDto) {
        /*
        1.Product表出货
            1.1.产品表具体批次的数量计算
            1.2.批次记录需要加上 其中有去向客户
                1.2.1产品表更新
        2.产品总览表更新
        3.orderDetail表改变出货状态
        4.order表内容重新计算
         */
//        1.Product表出货
        productService.deliver(deliverDto);
//        2.产品总览表更新
        productOverviewService.updateSelfById(deliverDto.getProducts().get(0).getProductId());
//        3.orderDetail表改变出货状态
        orderDetailService.deliver(deliverDto.getOrderDetail());
//        4.order表内容重新计算
        orderService.updateSelfByOrderId(deliverDto.getOrderDetail().getOrderId());

        return R.success("出货成功！订单交付进度等信息请刷新页面查看");
    }

    @PostMapping("/orderDetails/deliverBatch")
    @Transactional
    public R<Boolean> orderDetailsBatchDeliver(@RequestBody OrderDetailsBatchDeliverDto batchDeliverDto) {
        /*
        建立循环 依次出货
        检查是否足够出货
        不够的记录
            1.Product表出货
                1.1.产品表具体批次的数量计算
                1.2.批次记录需要加上 其中有去向客户
                    1.2.1产品表更新
            2.产品总览表更新
            3.orderDetail表改变出货状态
        4.order表内容重新计算
         */
        List<OrderDetail> orderDetails = batchDeliverDto.getOrderDetails();
        int numberOfFailures = 0;
        int numberOfSuccess = 0;

        for (OrderDetail orderDetail : orderDetails) {
            Long productId = orderDetail.getProductId();
            Integer remainNumber = productOverviewService.getById(productId).getNumber();
            //已出货状态 跳过
            if (orderDetail.getIsDelivered().equals(OrderDetail.DELIVERED))
                continue;
            //数量足够
            if (remainNumber >= orderDetail.getNumber()) {
//              1.Product表出货
                productService.deliverAuto(orderDetail);
//              2.产品总览表更新
                productOverviewService.updateSelfById(orderDetail.getProductId());
//              3.orderDetail表改变出货状态
                orderDetailService.deliver(orderDetail);
                numberOfSuccess++;
            } else {
                numberOfFailures++;
            }
        }

        //4.order表内容重新计算
        orderService.updateSelfByOrderId(orderDetails.get(0).getOrderId());

        if (numberOfFailures == 0) {
            return R.success("批量发货成功!");
        } else {
            return R.success("出货成功数:" + numberOfSuccess + ";出货失败数:" + numberOfFailures);
        }

    }

    @PutMapping("/orderDetails")
    @Transactional
    public R<Boolean> update4OrderDetail(@RequestBody OrderDetail detail) {
        /*
            1. 更新orderDetail表
            2. Order表内容重新计算
         */
//      1. 更新orderDetail表
        boolean operate1 = orderDetailService.updateById(detail);
//      2. Order表内容重新计算
        boolean operate2 = orderService.updateSelfByOrderId(detail.getOrderId());

        return operate1 && operate2 ?
                R.success("修改成功") :
                R.error("修改失败！数据不同步，自动刷新", Code.UPDATE_ERR);
    }

    @DeleteMapping("/orderDetails/{id}")
    @Transactional
    public R<Boolean> delete4OrderDetail(@PathVariable int id) {
         /*
            1. 更新orderDetail表
            2. Order表内容重新计算
         */
        OrderDetail orderDetail = orderDetailService.getById(id);
//      1. 更新orderDetail表
        boolean operate1 = orderDetailService.removeById(id);
//      2. Order表内容重新计算
        String orderId = orderDetail.getOrderId();
        boolean operate2 = orderService.updateSelfByOrderId(orderId);

        return operate1 && operate2 ?
                R.success("删除成功") :
                R.error("删除失败！数据不同步，自动刷新", Code.DELETE_ERR);
    }

    @GetMapping("/print/{orderId}")
    public ResponseEntity<Resource> printOrder(@PathVariable String orderId) throws IOException {
        LambdaQueryWrapper<Order> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Order::getOrderId, orderId);
        Order order = orderService.getOne(lqw);
        OrderDemoData orderDemoData = new OrderDemoData();
        BeanUtils.copyProperties(order, orderDemoData);
        orderDemoData.setTotalAmount(order.getAmount());
        orderDemoData.setAmountByChinese(NumberConverterUtil.convertToChineseNumber(orderDemoData.getTotalAmount()));

        LambdaQueryWrapper<OrderDetail> lqw1 = new LambdaQueryWrapper<>();
        lqw1.eq(OrderDetail::getOrderId, orderId);
        List<OrderDetail> orderDetails = orderDetailService.list(lqw1);

        String templateFileName = "src/main/resources/stastic/formDemo/order.xlsx";
        String fileName = String.format("order_%s.xlsx", System.currentTimeMillis());
        String filePath = "src/main/resources/stastic/temporary/" + fileName;

        try (ExcelWriter excelWriter = EasyExcel.write(filePath).withTemplate(templateFileName).build()) {
            WriteSheet writeSheet = EasyExcel.writerSheet().build();
            FillConfig fillConfig = FillConfig.builder().forceNewRow(Boolean.TRUE).build();
            excelWriter.fill(orderDetails, fillConfig, writeSheet);
            excelWriter.fill(orderDemoData, writeSheet);
            excelWriter.finish();
        }

        File file = new File(filePath);
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName);

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(file.length())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }


}
