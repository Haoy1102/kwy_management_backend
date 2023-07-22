package com.kwy.management;

import com.kwy.management.entity.PurchaseRecord;
import com.kwy.management.service.PurchaseRecordService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

/**
 * @author haoy
 * @description
 * @date 2023/7/10 14:58
 */
@SpringBootTest
public class MaterialPurcharseServiceTest {

    @Autowired
    private PurchaseRecordService purchaseRecordService;

    @Test
    void testPurchase(){
        PurchaseRecord record = new PurchaseRecord();
        record.setMaterialInfoId(10001L);
        record.setCategory("味精1");
        record.setSupplier("供货尚2");
//        record.setNumber(100.00);
        record.setPrice(20.00);
        record.setTotalAmount(2000.00);
        record.setLocation("东厂");
        record.setProducedDate(LocalDate.now());
        record.setStatus(1);
        record.setCreateDate(LocalDate.now());
        purchaseRecordService.purchase(record);
    }
}
