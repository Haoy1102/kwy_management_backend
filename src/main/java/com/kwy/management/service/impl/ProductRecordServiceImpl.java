package com.kwy.management.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kwy.management.entity.ProductRecord;
import com.kwy.management.mapper.ProductRecordMapper;
import com.kwy.management.service.ProductRecordService;
import org.springframework.stereotype.Service;

/**
 * @author haoy
 * @description
 * @date 2023/7/14 19:06
 */
@Service
public class ProductRecordServiceImpl extends ServiceImpl<ProductRecordMapper, ProductRecord> implements ProductRecordService {
}
