package com.kwy.management.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kwy.management.dto.GoodsOutForm;
import com.kwy.management.entity.*;
import com.kwy.management.mapper.GoodsMapper;
import com.kwy.management.mapper.GoodsRecordMapper;
import com.kwy.management.mapper.MatertialOverviewMapper;
import com.kwy.management.service.GoodsService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

/**
 * @author haoy
 * @description
 * @date 2023/7/10 12:22
 */
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private GoodsRecordMapper goodsRecordMapper;

    @Autowired
    private MatertialOverviewMapper matertialOverviewMapper;

    @Override
    public IPage<Goods> getPage(int currentPage, int pageSize, Goods goods) {
        LambdaQueryWrapper<Goods> lqw = new LambdaQueryWrapper<Goods>();
        lqw.like(goods.getGoodsId() != null, Goods::getGoodsId, goods.getGoodsId());
        lqw.like(Strings.isNotEmpty(goods.getCategory()),
                Goods::getCategory, goods.getCategory());
        lqw.like(Strings.isNotEmpty(goods.getSupplier()),
                Goods::getSupplier, goods.getSupplier());
        lqw.orderByDesc(Goods::getCreateTime);
//        lqw.gt(Goods::getNumber,0);
        IPage page = new Page(currentPage, pageSize);
        goodsMapper.selectPage(page, lqw);
        return page;
    }

    /**
     * 原材料出库
     *
     * @param outForm
     * @return
     */
    @Override
    @Transactional
    public boolean goodsOut(GoodsOutForm outForm) {
        /*
            1. 插入GoodsRecord 库存记录表 注意余量
            2. 总览表
                2.1 物品数量--
                2.2 如果有价格 value变动
            3. 更新Goods表，使余量-
         */
        Goods goods = goodsMapper.selectById(outForm.getGoodsId());

        if (outForm.getOperateNumber()>goods.getNumber()){
            return false;
        }

        // 1. 插入GoodsRecord 库存记录表 注意余量
        GoodsRecord goodsRecord = new GoodsRecord();
        BeanUtils.copyProperties(outForm, goodsRecord);
        goodsRecord.setCategoryId(goods.getCategoryId());
        goodsRecord.setCategory(goods.getCategory());
        goodsRecord.setMaterialInfoId(goods.getMaterialInfoId());
        goodsRecord.setSupplier(goods.getSupplier());
        goodsRecord.setOriginNumber(goods.getNumber());
        goodsRecord.setRemainNumber(goods.getNumber()-outForm.getOperateNumber());
        goodsRecord.setProducedDate(goods.getProducedDate());
        goodsRecord.setLocation(goods.getLocation());
        goodsRecord.setStatus(goods.getStatus());

        goodsRecordMapper.insert(goodsRecord);

        //2.总览表
        MaterialOverview overview = matertialOverviewMapper.selectById(goods.getCategoryId());

        overview.setNumber(overview.getNumber() - outForm.getOperateNumber());
        if (overview.getPrice() != null) {
            overview.setValue(overview.getPrice() * overview.getNumber());
        }
        matertialOverviewMapper.updateById(overview);

        // 3. 更新Goods表，使余量-
        goods.setNumber(goods.getNumber() - outForm.getOperateNumber());
        goodsMapper.updateById(goods);

        return true;
    }

    /**
     * 手动录入功能
     *
     * @param goods
     * @return
     */
    @Override
    @Transactional
    public boolean entryManual(Goods goods) {
        /*
         *  1.库存表save
         *  2.总览表数量++
         *  3.库存记录表记录新增
         */

//        1.库存表save
        goodsMapper.insert(goods);
        goods.setGoodsId(goods.getId());
        goodsMapper.updateById(goods);


//        2.总览表数量++
        MaterialOverview overview = matertialOverviewMapper.selectById(goods.getCategoryId());

        overview.setNumber(goods.getNumber() + overview.getNumber());
        overview.setUpdateDate(LocalDate.now());
        if (overview.getPrice() != null) {
            overview.setValue(overview.getPrice() * overview.getNumber());
        }
        matertialOverviewMapper.updateById(overview);

//        3.库存记录表记录新增
        GoodsRecord goodsRecord = new GoodsRecord();
        BeanUtils.copyProperties(goods, goodsRecord);
        goodsRecord.setOperateType(GoodsRecord.OPERATE_IN_MANUAL);
        goodsRecord.setOperateNumber(goods.getNumber());
        goodsRecord.setRemainNumber(goods.getNumber());
        goodsRecordMapper.insert(goodsRecord);
        return true;
    }

    /**
     * 更新某一批货品
     * @param newGoods
     * @return
     */
    @Override
    @Transactional
    public boolean update4Goods(Goods newGoods) {
        /*
        1.先检测数量有没有变化
        2.如果数量有变化 更新总览表
        3.更新记录表
        4.更新Goods表
         */
        Goods originGoods = goodsMapper.selectById(newGoods.getId());

        Integer changeNumber = newGoods.getNumber() - originGoods.getNumber();

//        1.先检测数量有没有变化
        if (changeNumber != 0) {
//            2.如果数量有变化 更新总览表
            MaterialOverview overview = matertialOverviewMapper.selectById(newGoods.getCategoryId());
            overview.setNumber(overview.getNumber() + changeNumber);
            overview.setValue(overview.getNumber() * overview.getPrice());
            matertialOverviewMapper.updateById(overview);
        }

//        3.更新记录表
        GoodsRecord record = new GoodsRecord();
        BeanUtils.copyProperties(newGoods, record);

        record.setOperateType(GoodsRecord.OPERATE_MANUAL);
        record.setOperateNumber(changeNumber);
        record.setRemainNumber(newGoods.getNumber());
        record.setOriginNumber(originGoods.getNumber());
        goodsRecordMapper.insert(record);

//        4.更新Product表
        goodsMapper.updateById(newGoods);

        return true;
    }

    @Override
    public boolean deleteGoods(int id) {
        /*
        1.批次记录添加记录
        2.商品总览中数量减少
        3.删除该条数据
         */

        Goods goods = goodsMapper.selectById(id);

//        1.批次记录添加记录
        GoodsRecord record = new GoodsRecord();
        BeanUtils.copyProperties(goods, record);

        record.setOperateType(GoodsRecord.OPERATE_DELETE);
        record.setOperateNumber(goods.getNumber());
        record.setRemainNumber(0);
        record.setOriginNumber(goods.getNumber());
        goodsRecordMapper.insert(record);

//        2.商品总览中数量减少
        MaterialOverview overview = matertialOverviewMapper.selectById(goods.getCategoryId());
        overview.setNumber(overview.getNumber() - goods.getNumber());
        overview.setValue(overview.getNumber() * overview.getPrice());
        matertialOverviewMapper.updateById(overview);

//        3.删除该条数据
        goodsMapper.deleteById(id);
        return true;
    }
}
