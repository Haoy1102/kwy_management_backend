package com.kwy.management.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kwy.management.entity.Goods;
import com.kwy.management.entity.GoodsRecord;
import com.kwy.management.entity.PurchaseRecord;
import com.kwy.management.mapper.GoodsMapper;
import com.kwy.management.mapper.GoodsRecordMapper;
import com.kwy.management.service.GoodsRecordService;
import com.kwy.management.service.GoodsService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author haoy
 * @description
 * @date 2023/7/10 12:23
 */
@Service
public class GoodsRecordServiceImpl extends ServiceImpl<GoodsRecordMapper, GoodsRecord> implements GoodsRecordService {

    @Autowired
    private GoodsRecordMapper goodsRecordMapper;

    @Override
    public IPage<GoodsRecord> getPage(int currentPage, int pageSize, GoodsRecord record) {
        LambdaQueryWrapper<GoodsRecord> lqw = new LambdaQueryWrapper<>();
        lqw.like(record.getId()!=null, GoodsRecord::getId, record.getId());
        lqw.like(record.getGoodsId()!=null, GoodsRecord::getGoodsId, record.getGoodsId());
        lqw.like(Strings.isNotEmpty(record.getCategory()),
                GoodsRecord::getCategory,record.getCategory());
        lqw.like(Strings.isNotEmpty(record.getSupplier()),
                GoodsRecord::getSupplier,record.getSupplier());
        lqw.orderByDesc(GoodsRecord::getUpdateTime);
        IPage page = new Page(currentPage, pageSize);
        goodsRecordMapper.selectPage(page, lqw);
        return page;
    }

    @Override
    public IPage<GoodsRecord> getPageSimple(Long goodsId) {
        LambdaQueryWrapper<GoodsRecord> lqw = new LambdaQueryWrapper<>();
        lqw.eq(GoodsRecord::getGoodsId, goodsId);
        IPage page = new Page(1, 10);
        goodsRecordMapper.selectPage(page, lqw);
        return page;
    }
}
