package com.kwy.management.controller;

import com.alibaba.excel.EasyExcel;
import com.kwy.management.entity.ExcleData.FillData;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.nio.file.Files;

/**
 * @author haoy
 * @description
 * @date 2023/7/13 14:14
 */
@RestController
@RequestMapping("/api")
public class ExcleController {

    @PostMapping("/printTicket")
    public ResponseEntity<byte[]> printTicket() {
        try {
            // 执行生成票据打印格式的代码
            String templateFileName = "src/main/resources/stastic/demo.xlsx";
            String fileName = "src/main/resources/stastic/simpleFill" + System.currentTimeMillis() + ".xlsx";
            FillData fillData = new FillData();
            fillData.setName("张三");
            fillData.setNumber(5.2);
            EasyExcel.write(fileName).withTemplate(templateFileName).sheet().doFill(fillData);

            // 将生成的文件发送到打印机进行打印
            // 这里可以使用你系统中的打印机相关的API或库来实现打印功能

            // 读取生成的Excel文件内容
            File file = new File(fileName);
            byte[] fileContent = Files.readAllBytes(file.toPath());

            // 设置响应头，告诉浏览器返回的是Excel文件
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDisposition(ContentDisposition.attachment().filename(file.getName()).build());

            // 返回Excel文件内容给前端
            return new ResponseEntity<>(fileContent, headers, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
