package com.kwy.management.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.fill.FillConfig;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kwy.management.comon.Code;
import com.kwy.management.comon.R;
import com.kwy.management.entity.*;
import com.kwy.management.entity.ExcleData.*;
import com.kwy.management.service.PurchaseRecordService;
import com.kwy.management.utils.NumberConverterUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

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
 * @date 2023/7/10 19:34
 */
@Slf4j
@RestController
@RequestMapping("/api/purchase")
public class PurchaseController {

    @Autowired
    private PurchaseRecordService purchaseRecordService;

    @Value("${myapp.file-path}")
    private String basicPath;


    @PostMapping
    public R<Boolean> savePurchase(@RequestBody PurchaseRecord purchaseRecord) {
        return purchaseRecordService.purchase(purchaseRecord) ?
                R.success("添加成功") :
                R.error("添加失败", Code.SAVE_ERR);
    }

    @DeleteMapping("{id}")
    public R<Boolean> delete(@PathVariable Long id) {
        return purchaseRecordService.removeById(id) ?
                R.success("删除成功") :
                R.error("删除失败！数据不同步，自动刷新", Code.DELETE_ERR);
    }

    @PutMapping
    public R<Boolean> update(@RequestBody PurchaseRecord purchaseRecord) {
        return purchaseRecordService.updateById(purchaseRecord) ?
                R.success("修改成功") :
                R.error("修改失败！数据不同步，自动刷新", Code.DELETE_ERR);
    }

    @GetMapping("/{materialInfoId}")
    public R<IPage<PurchaseRecord>> getPageSimple(@PathVariable Long materialInfoId) {
        IPage<PurchaseRecord> page = purchaseRecordService.getPageSimple(materialInfoId);
        return R.success(page);
    }

    @GetMapping("/{currentPage}/{pageSize}")
    public R<IPage<PurchaseRecord>> getPage(@PathVariable int currentPage, @PathVariable int pageSize, PurchaseRecord record) {
        IPage<PurchaseRecord> page = purchaseRecordService.getPage(currentPage, pageSize, record);
        return R.success(page);
    }

    @GetMapping("/printData")
    public ResponseEntity<Resource> checkBills(String startDate, String endDate) throws FileNotFoundException {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localStartDate = LocalDate.parse(startDate, formatter);
        LocalDate localEndDate = LocalDate.parse(endDate, formatter);

        int pageSize = 40; // 每页的记录数
        int currentPage = 1; // 当前页码
        String[] statusLabels = {"", "新鲜", "临期", "尽快使用"};



        String fileName = String.format("purRecord_%s.xlsx", System.currentTimeMillis());
        String filePath = basicPath + fileName;

        try (ExcelWriter excelWriter = EasyExcel.write(filePath, PurchaseDataDemo.class).build()) {

            LambdaQueryWrapper<PurchaseRecord> recordlqw = new LambdaQueryWrapper<>();
            recordlqw.ge(PurchaseRecord::getCreateDate, localStartDate)
                    .le(PurchaseRecord::getCreateDate, localEndDate);

            while (true) {
                // 创建分页对象
                Page<PurchaseRecord> page = new Page<>(currentPage, pageSize);
                // 执行分页查询
                IPage<PurchaseRecord> resultPage = purchaseRecordService.page(page, recordlqw);
                // 获取当前页的数据列表
                List<PurchaseRecord> records = resultPage.getRecords();
                ArrayList<PurchaseDataDemo> demos = new ArrayList<>();

                for (PurchaseRecord record : records) {
                    PurchaseDataDemo purchaseDataDemo = new PurchaseDataDemo();
                    BeanUtils.copyProperties(record, purchaseDataDemo);
                    purchaseDataDemo.setStatusLabel(statusLabels[record.getStatus()]);
                    demos.add(purchaseDataDemo);
                }
                WriteSheet writeSheet = EasyExcel.writerSheet("采购记录").build();
                excelWriter.write(demos, writeSheet);
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
