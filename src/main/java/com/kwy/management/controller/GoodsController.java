package com.kwy.management.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.kwy.management.comon.Code;
import com.kwy.management.comon.R;
import com.kwy.management.dto.GoodsOutForm;
import com.kwy.management.entity.Customer;
import com.kwy.management.entity.Goods;
import com.kwy.management.entity.GoodsRecord;
import com.kwy.management.entity.PurchaseRecord;
import com.kwy.management.service.GoodsRecordService;
import com.kwy.management.service.GoodsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author haoy
 * @description
 * @date 2023/7/11 11:21
 */
@Slf4j
@RestController
@RequestMapping("/api/goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private GoodsRecordService goodsRecordService;


    /**
     * 库存录入
     * @param goods
     * @return
     */
    @PostMapping
    public R<Boolean> entryManual(@RequestBody Goods goods) {
        return goodsService.entryManual(goods) ?
                R.success("录入成功") :
                R.error("录入失败，请检查数据", Code.SAVE_ERR);
    }

    /**
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public R<Boolean> delete4Goods(@PathVariable int id) {
        return goodsService.deleteGoods(id) ?
                R.success("删除成功") :
                R.error("删除失败！数据不同步，自动刷新", Code.DELETE_ERR);
    }

    /**
     * @param goods
     * @return
     */
    @PutMapping
    public R<Boolean> update4Goods(@RequestBody Goods goods) {
        return goodsService.update4Goods(goods) ?
                R.success("修改成功!") :
                R.error("修改失败!数据不同步，自动刷新", Code.UPDATE_ERR);
    }

    /**
     * 返回页面数据
     *
     * @param currentPage
     * @param pageSize
     * @return
     */
    @GetMapping("/{currentPage}/{pageSize}")
    public R<IPage<Goods>> getPage(@PathVariable int currentPage, @PathVariable int pageSize, Goods goods) {
        IPage<Goods> page= goodsService.getPage(currentPage, pageSize,goods);;
        //如果当前页码值大于了总页码值，那么重新执行查询操作，使用最大页码值作为当前页码值
        if( currentPage > page.getPages()){
            page = goodsService.getPage((int)page.getPages(), pageSize,goods);
        }
        return R.success(page);
    }

    @PostMapping("/out")
    public R<Boolean> goodsOut(@RequestBody GoodsOutForm outForm){
        return goodsService.goodsOut(outForm)?
                R.success("出库成功"):
                R.error("出库失败！数据不同步，自动刷新",Code.SYSTEM_ERR);
    }

    @PutMapping("/records")
    public R<Boolean> update4GoodsRecord(@RequestBody GoodsRecord record) {
        return goodsRecordService.updateById(record) ?
                R.success("修改成功!") :
                R.error("修改失败！数据不同步，自动刷新", Code.UPDATE_ERR);
    }

    @GetMapping("/records/{goodsId}")
    public R<IPage<GoodsRecord>> getPageSimple(@PathVariable Long goodsId){
        IPage<GoodsRecord> page= goodsRecordService.getPageSimple(goodsId);
        return R.success(page);
    }

    @GetMapping("/records/{currentPage}/{pageSize}")
    public R<IPage<GoodsRecord>> getPage4GoodsRecord(@PathVariable int currentPage, @PathVariable int pageSize, GoodsRecord record) {
        IPage<GoodsRecord> page= goodsRecordService.getPage(currentPage, pageSize,record);;
        //如果当前页码值大于了总页码值，那么重新执行查询操作，使用最大页码值作为当前页码值
        if( currentPage > page.getPages()){
            page = goodsRecordService.getPage((int)page.getPages(), pageSize,record);
        }
        return R.success(page);
    }

}
