package com.kwy.management.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kwy.management.dto.GoodsOutForm;
import com.kwy.management.entity.Customer;
import com.kwy.management.entity.Goods;
import com.kwy.management.entity.GoodsRecord;

/**
 * @author haoy
 * @description
 * @date 2023/7/10 12:17
 */
public interface GoodsService extends IService<Goods> {
    IPage<Goods> getPage(int currentPage, int pageSize, Goods goods);

    boolean goodsOut(GoodsOutForm goodsRecord);

    boolean entryManual(Goods goods);

    boolean update4Goods(Goods newGoods);

    boolean deleteGoods(int id);
}
