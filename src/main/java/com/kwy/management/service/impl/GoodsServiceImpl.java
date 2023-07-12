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
        IPage page = new Page(currentPage, pageSize);
        goodsMapper.selectPage(page, lqw);
        return page;
    }

    @Override
    @Transactional
    public boolean goodsOut(GoodsOutForm outForm) {
        /*
            1. 更新Goods表，使余量-
            2. 插入GoodsRecord 库存记录表 注意余量
            3. 总览表
                3.1 物品数量--
                3.2 如果有价格 value变动
         */
        // 1. 更新Goods表，使余量-
        Goods goods = new Goods();
        String[] ignoreProperties = {"note"};
        BeanUtils.copyProperties(outForm, goods,ignoreProperties);
        goods.setNumber(goods.getNumber() - outForm.getOperateNumber());
        goodsMapper.updateById(goods);

        // 2. 插入GoodsRecord 库存记录表 注意余量
        GoodsRecord goodsRecord = new GoodsRecord();
        BeanUtils.copyProperties(outForm, goodsRecord);
        goodsRecord.setId(null);
        goodsRecord.setRemainNumber(goods.getNumber());
        goodsRecordMapper.insert(goodsRecord);

        //3.总览表
        LambdaQueryWrapper<MaterialOverview> materialOverviewlqw = new LambdaQueryWrapper<>();
        materialOverviewlqw.eq(MaterialOverview::getCategory, goods.getCategory());
        MaterialOverview overview = matertialOverviewMapper.selectOne(materialOverviewlqw);
        if (overview == null) {
            return false;
        } else {
            overview.setNumber(overview.getNumber()-goods.getNumber());
            overview.setUpdateDate(LocalDate.now());
            if (overview.getPrice() != null) {
                overview.setValue(overview.getPrice() * overview.getNumber());
            }
            matertialOverviewMapper.updateById(overview);
        }

        return true;
    }

    @Override
    @Transactional
    public boolean inventoryEntry(Goods goods) {
        /*
         *  1.库存表save
         *  2.总览表数量++
         *      2.1 如果有 数量++
         *      2.2 如果没有 新增
         *  3.库存记录表记录新增
         */

//        1.库存表save
        goodsMapper.insert(goods);

//        2.总览表数量++
        LambdaQueryWrapper<MaterialOverview> materialOverviewlqw = new LambdaQueryWrapper<>();
        materialOverviewlqw.eq(MaterialOverview::getCategory, goods.getCategory());
        MaterialOverview overview = matertialOverviewMapper.selectOne(materialOverviewlqw);
        if (overview == null) {
            MaterialOverview newOverview = new MaterialOverview();
            newOverview.setCategory(goods.getCategory());
            newOverview.setNumber(goods.getNumber());
            newOverview.setUpdateDate(LocalDate.now());
            matertialOverviewMapper.insert(newOverview);
        } else {
            overview.setNumber(goods.getNumber() + overview.getNumber());
            overview.setUpdateDate(LocalDate.now());
            if (overview.getPrice() != null) {
                overview.setValue(overview.getPrice() * overview.getNumber());
            }
            matertialOverviewMapper.updateById(overview);
        }
//        3.库存记录表记录新增
        GoodsRecord goodsRecord = new GoodsRecord();
        BeanUtils.copyProperties(goods, goodsRecord);
        goodsRecord.setOperateType(GoodsRecord.OPERATE_IN_MANUAL);
        goodsRecord.setOperateNumber(goods.getNumber());
        goodsRecord.setRemainNumber(goods.getNumber());
        goodsRecordMapper.insert(goodsRecord);
        return true;
    }
}
