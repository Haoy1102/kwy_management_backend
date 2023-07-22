package com.kwy.management.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kwy.management.entity.*;
import com.kwy.management.mapper.*;
import com.kwy.management.service.PurchaseRecordService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

/**
 * @author haoy
 * @description
 * @date 2023/7/10 12:24
 */
@Service
public class PurchaseRecordServiceImpl extends ServiceImpl<PurchaseRecordMapper, PurchaseRecord> implements PurchaseRecordService {

    @Autowired
    private PurchaseRecordMapper purchaseRecordMapper;

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private GoodsRecordMapper goodsRecordMapper;

    @Autowired
    private MatertialOverviewMapper matertialOverviewMapper;

    @Autowired
    private MatertialInfoMapper matertialInfoMapper;

    @Override
    @Transactional
    public boolean purchase(PurchaseRecord record) {
        /*
        1. PurchaseRecord表插入数据
        2. Goods 库存表中插入数据 设置货号等
        3. GoodsRecord 库存记录表插入数据
        4. MaterialInfo 原料信息表中 最近采购单价更改
        5. MaterialOverview 材料总览中
            5.1 如果存在该品类 总量更改 最近入库日期更改 最新成交价更改 估值更改
            5.2 如果不存在 加上该种类 设置总量 最近入库 单价 估值
         */


//        2. Goods 库存表中插入数据 设置货号等
        Goods goods = new Goods();
        goods.setCategoryId(record.getCategoryId());
        goods.setCategory(record.getCategory());
        goods.setMaterialInfoId(record.getMaterialInfoId());
        goods.setSupplier(record.getSupplier());
        goods.setNumber(record.getNumber());
        goods.setLocation(record.getLocation());
        goods.setProducedDate(record.getProducedDate());
        goods.setStatus(record.getStatus());
        goods.setNote(record.getNote());
        goodsMapper.insert(goods);
        goods.setGoodsId(goods.getId());
        goodsMapper.updateById(goods);

        //设置goodsId
        Long goodsId = goods.getGoodsId();

//        1. PurchaseRecord表插入数据
        record.setCreateDate(LocalDate.now());
        record.setGoodsId(goodsId);
        purchaseRecordMapper.insert(record);


//        3. GoodsRecord 库存记录表插入数据
        GoodsRecord goodsRecord = new GoodsRecord();
        goodsRecord.setGoodsId(goodsId);
        goodsRecord.setCategoryId(record.getCategoryId());
        goodsRecord.setMaterialInfoId(record.getMaterialInfoId());
        goodsRecord.setCategory(record.getCategory());
        goodsRecord.setSupplier(record.getSupplier());
        goodsRecord.setOperateType(GoodsRecord.OPERATE_PURCHASE);
        goodsRecord.setOperateNumber(record.getNumber());
        goodsRecord.setRemainNumber(record.getNumber());
        goodsRecord.setProducedDate(record.getProducedDate());
        goodsRecord.setLocation(record.getLocation());
        goodsRecord.setStatus(record.getStatus());
        goodsRecord.setNote(record.getNote());
        goodsRecordMapper.insert(goodsRecord);

//        4. MaterialInfo 原料信息表中 最近采购单价更改
        MaterialInfo materialInfo = matertialInfoMapper.selectById(record.getMaterialInfoId());
        materialInfo.setLatestPrice(record.getPrice());
        matertialInfoMapper.updateById(materialInfo);

//        5. MaterialOverview 材料总览中
//          5.1 如果存在该品类 总量更改 最近入库日期更改 最新成交价更改 估值更改
        MaterialOverview overview = matertialOverviewMapper.selectById(record.getCategoryId());
        overview.setNumber(record.getNumber() + overview.getNumber());
        overview.setUpdateDate(record.getCreateDate());
        overview.setPrice(record.getPrice());
        overview.setValue(overview.getPrice() * overview.getNumber());
        matertialOverviewMapper.updateById(overview);

        return true;
    }

    @Override
    public IPage getPageSimple(Long materialInfoId) {
        LambdaQueryWrapper<PurchaseRecord> lqw = new LambdaQueryWrapper<PurchaseRecord>();
        lqw.eq(PurchaseRecord::getMaterialInfoId, materialInfoId)
                .orderByDesc(PurchaseRecord::getCreateTime);
        IPage page = new Page(1, 10);
        purchaseRecordMapper.selectPage(page, lqw);
        return page;
    }

    @Override
    public IPage<PurchaseRecord> getPage(int currentPage, int pageSize, PurchaseRecord record) {
        LambdaQueryWrapper<PurchaseRecord> lqw = new LambdaQueryWrapper<PurchaseRecord>();
        lqw.like(record.getId() != null, PurchaseRecord::getId, record.getId());
        lqw.like(record.getGoodsId() != null, PurchaseRecord::getGoodsId, record.getGoodsId());
        lqw.like(Strings.isNotEmpty(record.getCategory()),
                PurchaseRecord::getCategory, record.getCategory());
        lqw.like(Strings.isNotEmpty(record.getSupplier()),
                PurchaseRecord::getSupplier, record.getSupplier());
        lqw.orderByDesc(PurchaseRecord::getCreateTime);
        IPage page = new Page(currentPage, pageSize);
        purchaseRecordMapper.selectPage(page, lqw);
        return page;
    }
}
