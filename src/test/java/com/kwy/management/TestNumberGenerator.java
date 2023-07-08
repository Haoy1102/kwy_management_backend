package com.kwy.management;

import com.kwy.management.utils.OrderNumberGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author haoy
 * @description
 * @date 2023/7/8 15:03
 */
@SpringBootTest
public class TestNumberGenerator {


    @Test
    void testNumberGenerator(){
        System.out.println(OrderNumberGenerator.generateOrderNumber("10001"));
        System.out.println(OrderNumberGenerator.generateOrderNumber("10001").length());
    }
    @Test
    void test(){
        System.getProperty("user.dir");
    }

}
