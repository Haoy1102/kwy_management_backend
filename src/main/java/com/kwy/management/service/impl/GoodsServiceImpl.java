package com.kwy.management.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kwy.management.entity.Goods;
import com.kwy.management.mapper.GoodsMapper;
import com.kwy.management.service.GoodsService;
import org.springframework.stereotype.Service;

/**
 * @author haoy
 * @description
 * @date 2023/7/10 12:22
 */
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements GoodsService {
}
