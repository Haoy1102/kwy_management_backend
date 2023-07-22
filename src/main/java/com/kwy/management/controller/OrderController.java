package com.kwy.management.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.util.MapUtils;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.fill.FillConfig;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kwy.management.comon.Code;
import com.kwy.management.comon.R;
import com.kwy.management.dto.OrderAddDto;
import com.kwy.management.dto.OrderDetailsBatchDeliverDto;
import com.kwy.management.dto.OrderDetailsDeliverDto;
import com.kwy.management.entity.ExcleData.*;
import com.kwy.management.entity.Order;
import com.kwy.management.entity.OrderDetail;
import com.kwy.management.entity.PurchaseRecord;
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
import java.io.*;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

    /**
     * 返回订单页面
     *
     * @param currentPage
     * @param pageSize
     * @param order
     * @return
     */
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

    /**
     * 返回客户一年的订单数据
     *
     * @param customerId
     * @return
     */
    @GetMapping("/customers/{customerId}")
    public R<List<Order>> getOrdersOneYearByCustomerId(@PathVariable Long customerId) {
        List<Order> orders = orderService.getOrdersOneYearByCustomerId(customerId);
        return R.success(orders);
    }

    /**
     * 查询订单内容
     *
     * @param orderId
     * @return
     */
    @GetMapping("/orderDetails/{orderId}")
    public R<List<OrderDetail>> getOrderDetailsByOrderId(@PathVariable String orderId) {
        LambdaQueryWrapper<OrderDetail> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(OrderDetail::getOrderId, orderId);
        List<OrderDetail> list = orderDetailService.list(queryWrapper);
        return R.success(list);
    }

    /**
     * 订单内容出货
     *
     * @param deliverDto
     * @return
     */
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

    /**
     * 订单内容批量出货
     *
     * @param batchDeliverDto
     * @return
     */
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

    /**
     * 编辑单条orderDetail操作
     *
     * @param detail
     * @return
     */
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

    /**
     * 删除单条orderDetail操作
     *
     * @param id
     * @return
     */
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

    /**
     * 返回订单的Excel
     *
     * @param orderId
     * @return
     * @throws IOException
     */
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

    /**
     * 导出所有订单数据
     *
     * @param
     * @return
     * @throws IOException
     */
    @GetMapping("/printData")
    public ResponseEntity<Resource> checkBills(String startDate, String endDate) throws FileNotFoundException {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localStartDate = LocalDate.parse(startDate, formatter);
        LocalDate localEndDate = LocalDate.parse(endDate, formatter);

        LocalDateTime localStartDateTime = localStartDate.atStartOfDay();
        LocalDateTime localEndDateTime = localEndDate.atStartOfDay().plusDays(1); // 加一天，包括结束日期当天

        String fileName = String.format("orderRecord_%s.xlsx", System.currentTimeMillis());
        String filePath = "src/main/resources/stastic/temporary/" + fileName;


        String[] statusLabels = {"","未制作", "制作中", "部分交付", "待回款", "完成", "返厂", "作废"};
        String[] isDeliveredLabels = {"未发货", "发货"};

        try (ExcelWriter excelWriter = EasyExcel.write(filePath).build()) {

            int pageSize = 20; // 每页的记录数
            int currentPage = 1; // 当前页码
            // 创建查询条件
            LambdaQueryWrapper<Order> orderlqw = new LambdaQueryWrapper<>();
            orderlqw.ge(Order::getCreateDate, localStartDate)
                    .le(Order::getCreateDate, localEndDate);

            while (true) {
                // 创建分页对象
                Page<Order> page = new Page<>(currentPage, pageSize);
                // 执行分页查询
                IPage<Order> resultPage = orderService.page(page, orderlqw);
                // 获取当前页的数据列表
                List<Order> orders = resultPage.getRecords();
                ArrayList<OrderDemo> demos = new ArrayList<>();

                for (Order order : orders) {
                    OrderDemo demo = new OrderDemo();
                    BeanUtils.copyProperties(order, demo);
                    demo.setStatusLabel(statusLabels[order.getStatus()]);
                    demos.add(demo);
                }

                // 处理当前页的数据，进行分次写入操作
//                try (ExcelWriter excelWriter = EasyExcel.write(fileName, OrderDetailDemo.class).build()) {
                WriteSheet writeSheet = EasyExcel.writerSheet(0, "订单记录").head(OrderDemo.class).build();
                excelWriter.write(demos, writeSheet);
//                }
                // 判断是否还有下一页
                if (currentPage >= resultPage.getPages()) {
                    break;
                }
                // 更新当前页码
                currentPage++;

            }

            currentPage = 1; // 当前页码
            // 创建查询条件
            LambdaQueryWrapper<OrderDetail> detaillqw = new LambdaQueryWrapper<>();
            detaillqw.ge(OrderDetail::getCreateTime, localStartDateTime)
                    .le(OrderDetail::getCreateTime, localEndDateTime);

            while (true) {
                // 创建分页对象
                Page<OrderDetail> page = new Page<>(currentPage, pageSize);
                // 执行分页查询
                IPage<OrderDetail> resultPage = orderDetailService.page(page, detaillqw);
                // 获取当前页的数据列表
                List<OrderDetail> orderDetails = resultPage.getRecords();
                ArrayList<OrderDetailDemo> detailDemos = new ArrayList<>();

                for (OrderDetail detail : orderDetails) {
                    OrderDetailDemo orderDetailDemo = new OrderDetailDemo();
                    BeanUtils.copyProperties(detail, orderDetailDemo);
                    orderDetailDemo.setIsDeliveredLabel(isDeliveredLabels[detail.getIsDelivered()]);
                    detailDemos.add(orderDetailDemo);
                }
                // 处理当前页的数据，进行分次写入操作
//                try (ExcelWriter excelWriter = EasyExcel.write(fileName).build()) {
                WriteSheet writeSheet = EasyExcel.writerSheet(1, "订单明细记录").head(OrderDetailDemo.class).build();
                excelWriter.write(detailDemos, writeSheet);
//                }
                // 判断是否还有下一页
                if (currentPage >= resultPage.getPages()) {
                    break;
                }
                // 更新当前页码
                currentPage++;
            }
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
