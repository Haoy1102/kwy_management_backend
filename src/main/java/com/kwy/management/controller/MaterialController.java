package com.kwy.management.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.kwy.management.comon.Code;
import com.kwy.management.comon.R;
import com.kwy.management.entity.Customer;
import com.kwy.management.entity.MaterialInfo;
import com.kwy.management.entity.MaterialOverview;
import com.kwy.management.entity.PurchaseRecord;
import com.kwy.management.service.MaterialInfoService;
import com.kwy.management.service.MaterialOverviewService;
import com.kwy.management.service.PurchaseRecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author haoy
 * @description
 * @date 2023/7/10 12:28
 */
@Slf4j
@RestController
@RequestMapping("/api/materials")
public class MaterialController {

    @Autowired
    private MaterialInfoService materialInfoService;

    @Autowired
    private MaterialOverviewService materialOverviewService;


    @PostMapping
    public R<Boolean> save(@RequestBody MaterialInfo materialInfo) {
        return materialInfoService.save(materialInfo) ?
                R.success("添加成功") :
                R.error("添加失败", Code.SAVE_ERR);
    }

    @DeleteMapping("/{id}")
    public R<Boolean> delete(@PathVariable Long id) {
        return materialInfoService.removeById(id) ?
                R.success("删除成功") :
                R.error("删除失败！数据不同步，自动刷新", Code.DELETE_ERR);
    }

    @PutMapping
    public R<Boolean> update(@RequestBody MaterialInfo materialInfo) {
        return materialInfoService.updateById(materialInfo) ?
                R.success("修改成功") :
                R.error("修改失败！数据不同步，自动刷新", Code.UPDATE_ERR);
    }

    @GetMapping("/{currentPage}/{pageSize}")
    public R<IPage<MaterialInfo>> getPage(@PathVariable int currentPage,
                                          @PathVariable int pageSize,
                                          MaterialInfo materialInfo) {
        IPage<MaterialInfo> page = materialInfoService.getPage(currentPage, pageSize, materialInfo);
        //如果当前页码值大于了总页码值，那么重新执行查询操作，使用最大页码值作为当前页码值
        if (currentPage > page.getPages()) {
            page = materialInfoService.getPage((int) page.getPages(), pageSize, materialInfo);
        }
        return R.success(page);
    }


    @PostMapping("/overviews")
    public R<Boolean> getPage4Overview(@RequestBody MaterialOverview overview) {
        return materialOverviewService.save(overview) ?
                R.success("添加成功") :
                R.error("添加失败", Code.SAVE_ERR);
    }

    @DeleteMapping("/overviews/{id}")
    public R<Boolean> delete4Overviews(@PathVariable Long id) {
        if (materialOverviewService.getById(id).getNumber()==0){
            return materialOverviewService.removeById(id) ?
                    R.success("删除成功") :
                    R.error("删除失败！数据不同步，自动刷新", Code.DELETE_ERR);
        }
        return R.error("删除失败！库存数量不为0", Code.DELETE_ERR);
    }

    @GetMapping("/overviews/{currentPage}/{pageSize}")
    public R<IPage<MaterialOverview>> getPage4Overview(@PathVariable int currentPage,
                                                       @PathVariable int pageSize,
                                                       MaterialOverview overview) {
        IPage<MaterialOverview> page = materialOverviewService.getPage(currentPage, pageSize, overview);
        //如果当前页码值大于了总页码值，那么重新执行查询操作，使用最大页码值作为当前页码值
        if (currentPage > page.getPages()) {
            page = materialOverviewService.getPage((int) page.getPages(), pageSize, overview);
        }
        return R.success(page);
    }

    @PutMapping("/overviews")
    public R<Boolean> update4Overview(@RequestBody MaterialOverview overview) {
        System.out.println(overview);
        return materialOverviewService.updateById(overview) ?
                R.success("修改成功") :
                R.error("修改失败！数据不同步，自动刷新", Code.DELETE_ERR);
    }

    @GetMapping()
    public R<List<MaterialInfo>> getAll(){
        List<MaterialInfo> list = materialInfoService.list();
        return !list.isEmpty()?
                R.success(list):
                R.error("采购管理表没有信息,请先添加采购信息",Code.GET_ERR);
    }

    @GetMapping("/overviews")
    public R<List<MaterialOverview>> getAll4Overview(){
        List<MaterialOverview> list = materialOverviewService.list();
        return !list.isEmpty()?
                R.success(list):
                R.error("原料总览表没有信息,请先添加原料信息",Code.GET_ERR);
    }

}
