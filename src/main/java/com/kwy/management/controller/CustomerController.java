package com.kwy.management.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.fill.FillConfig;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.kwy.management.comon.Code;
import com.kwy.management.comon.R;
import com.kwy.management.dto.CustomerDto;
import com.kwy.management.entity.Customer;
import com.kwy.management.entity.ExcleData.CheckFormDemo;
import com.kwy.management.entity.ExcleData.CheckFormDetailDemo;
import com.kwy.management.entity.ExcleData.CheckFormPaymentDemo;
import com.kwy.management.entity.Order;
import com.kwy.management.entity.OrderDetail;
import com.kwy.management.entity.PaymentDetail;
import com.kwy.management.service.CustomerService;
import com.kwy.management.service.OrderDetailService;
import com.kwy.management.service.OrderService;
import com.kwy.management.service.PaymentDetailService;
import com.kwy.management.utils.NumberConverterUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.jdbc.Null;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sun.rmi.runtime.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author haoy
 * @description
 * @date 2023/7/7 11:26
 */
@Slf4j
@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Value("${myapp.file-path}")
    private String basicPath;

    @Value("${myapp.template-checkForm-path}")
    private String templateFilePath;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderDetailService orderDetailService;

    @Autowired
    private PaymentDetailService paymentDetailService;

    /**
     * @param customer
     * @return
     */
    @PostMapping
    public R<Boolean> save(@RequestBody Customer customer) {
        return customerService.save(customer) ?
                R.success("添加成功") :
                R.error("添加失败，请检查数据。", Code.SAVE_ERR);
    }

    /**
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public R<Boolean> delete(@PathVariable int id) {
        return customerService.removeById(id) ?
                R.success("删除成功") :
                R.error("删除失败！数据不同步，自动刷新。", Code.DELETE_ERR);
    }

    /**
     * @param customer
     * @return
     */
    @PutMapping
    public R<Boolean> update(@RequestBody Customer customer) {
        return customerService.updateById(customer) ?
                R.success("修改成功!") :
                R.error("修改失败!数据不同步，自动刷新", Code.UPDATE_ERR);
    }

    /**
     * 返回页面数据
     *
     * @param currentPage
     * @param pageSize
     * @return
     */
    @GetMapping("/{currentPage}/{pageSize}")
    public R<IPage<Customer>> getPage(@PathVariable int currentPage, @PathVariable int pageSize, Customer customer) {
        IPage<Customer> page = customerService.getPage(currentPage, pageSize, customer);
        ;
        //如果当前页码值大于了总页码值，那么重新执行查询操作，使用最大页码值作为当前页码值
        if (currentPage > page.getPages()) {
            page = customerService.getPage((int) page.getPages(), pageSize, customer);
        }
        return R.success(page);
    }

    /**
     * 返回所有用户数据
     * @return
     */
    @GetMapping()
    public R<List<Customer>> getAll() {
        List<Customer> customerList = customerService.list();
        return !customerList.isEmpty() ?
                R.success(customerList) :
                R.error("获取客户数据失败", Code.GET_ERR);
    }

    /**
     * 返回用户详细的统计数据
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public R<CustomerDto> getDetails(@PathVariable Long id) {
        CustomerDto customerDto = customerService.getDetails(id);
        return R.success(customerDto);
    }

    /**
     * 返回对账单.xlsx文件
     * @param customerId
     * @param startDate
     * @param endDate
     * @return
     * @throws FileNotFoundException
     */
    @GetMapping("/checkBills/{customerId}")
    public ResponseEntity<Resource> checkBills(@PathVariable Long customerId, String startDate, String endDate) throws FileNotFoundException {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localStartDate = LocalDate.parse(startDate, formatter);
        LocalDate localEndDate = LocalDate.parse(endDate, formatter);

        LambdaQueryWrapper<Order> orderLambdaQueryWrapper = new LambdaQueryWrapper<>();
        orderLambdaQueryWrapper.eq(Order::getCustomerId, customerId)
                .ge(Order::getCreateDate, localStartDate)
                .le(Order::getCreateDate, localEndDate)
                .orderByDesc(Order::getCreateDate);

        List<Order> orders = orderService.list(orderLambdaQueryWrapper);

        //存储表格的单项数据
        CheckFormDemo checkFormDemo = new CheckFormDemo();
        checkFormDemo.setCustomer(orders.get(0).getCustomer());
        checkFormDemo.setStartDate(localStartDate);
        checkFormDemo.setEndDate(localEndDate);
        //存储所有需要对账的数据
        List<CheckFormDetailDemo> checkFormDetailList = new ArrayList<>();
        //存储所有回款记录
        List<CheckFormPaymentDemo> checkFormPaymentList = new ArrayList<>();

        //设置对账单部分
        Double totalAmount = 0.0;
        Double totalPayment = 0.0;
        for (Order order : orders) {
            LambdaQueryWrapper<OrderDetail> detailLambdaQueryWrapper = new LambdaQueryWrapper<>();
            //所有被选中订单的订单明细(已出货)
            detailLambdaQueryWrapper.eq(OrderDetail::getOrderId, order.getOrderId())
                    .eq(OrderDetail::getIsDelivered, OrderDetail.DELIVERED);
            List<OrderDetail> orderDetails = orderDetailService.list(detailLambdaQueryWrapper);
            //添加所有对账的数据
            for (OrderDetail detail : orderDetails) {
                CheckFormDetailDemo checkFormDetailDemo = new CheckFormDetailDemo();
                BeanUtils.copyProperties(detail, checkFormDetailDemo);
                checkFormDetailDemo.setOrderDate(order.getCreateDate());
                checkFormDetailList.add(checkFormDetailDemo);
                totalAmount += detail.getAmount();
            }

            //设置回款记录部分
            LambdaQueryWrapper<PaymentDetail> paymentWrapper = new LambdaQueryWrapper<>();
            paymentWrapper.eq(PaymentDetail::getOrderId, order.getOrderId());
            List<PaymentDetail> paymentDetails = paymentDetailService.list(paymentWrapper);
            //添加所有回款数据
            for (PaymentDetail paymentDetail : paymentDetails) {
                CheckFormPaymentDemo checkFormPaymentDemo = new CheckFormPaymentDemo();
                BeanUtils.copyProperties(paymentDetail,checkFormPaymentDemo);
                checkFormPaymentDemo.setPayAmount(paymentDetail.getAmount());
                checkFormPaymentDemo.setPayNote(paymentDetail.getNote());
                checkFormPaymentList.add(checkFormPaymentDemo);
                totalPayment+=paymentDetail.getAmount();
            }

        }
        checkFormDemo.setTotalAmount(totalAmount);
        checkFormDemo.setAmountByChinese(NumberConverterUtil.convertToChineseNumber(checkFormDemo.getTotalAmount()));
        checkFormDemo.setTotalPayment(totalPayment);
        checkFormDemo.setTotalDebt(checkFormDemo.getTotalAmount()-checkFormDemo.getTotalPayment());


        String templateFileName = templateFilePath;
        String fileName = String.format("check_%s.xlsx", System.currentTimeMillis());
        String filePath = basicPath + fileName;
        try (ExcelWriter excelWriter = EasyExcel.write(filePath).withTemplate(templateFileName).build()) {
            WriteSheet sheetCheck = EasyExcel.writerSheet(0,"对账单").build();
            WriteSheet sheetPayment = EasyExcel.writerSheet(1,"回款记录").build();

            FillConfig fillConfig = FillConfig.builder().forceNewRow(Boolean.TRUE).build();

            excelWriter.fill(checkFormDemo, sheetCheck);
            excelWriter.fill(checkFormDetailList, fillConfig, sheetCheck);

            excelWriter.fill(checkFormDemo, sheetPayment);
            excelWriter.fill(checkFormPaymentList, fillConfig, sheetPayment);
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
