package com.kwy.management;

import com.kwy.management.entity.PaymentDetail;
import com.kwy.management.service.PaymentDetailService;
import org.apache.logging.log4j.util.Strings;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

/**
 * @author haoy
 * @description
 * @date 2023/7/9 16:47
 */
@SpringBootTest
public class PaymentDetailsServiceTest {

    @Autowired
    private PaymentDetailService service;

    @Test
    void testGetByOrderId() {
        String orderId = "2023070915R210027CJH";
        System.out.println(Strings.isNotBlank(orderId));
//        System.out.println(service.getOrderPaymentDetails(orderId));
    }

    @Test
    void testAddItem() {
        PaymentDetail detail = new PaymentDetail();
        detail.setOrderId("2023070915R210027CJH");
        detail.setPayDate(LocalDate.now());
        detail.setAmount(1000.00);
        service.addItem(detail);
    }

    @Test
    void deleteById() {
        service.deleteById(10015L);
    }
}
