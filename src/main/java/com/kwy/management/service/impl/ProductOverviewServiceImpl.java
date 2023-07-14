package com.kwy.management.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kwy.management.entity.ProductOverview;
import com.kwy.management.mapper.ProductOverviewMapper;
import com.kwy.management.service.ProductOverviewService;
import org.springframework.stereotype.Service;

/**
 * @author haoy
 * @description
 * @date 2023/7/14 19:06
 */
@Service
public class ProductOverviewServiceImpl extends ServiceImpl<ProductOverviewMapper, ProductOverview> implements ProductOverviewService {
}
