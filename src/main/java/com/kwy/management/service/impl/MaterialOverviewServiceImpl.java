package com.kwy.management.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kwy.management.entity.MaterialInfo;
import com.kwy.management.entity.MaterialOverview;
import com.kwy.management.mapper.MatertialOverviewMapper;
import com.kwy.management.service.MaterialOverviewService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author haoy
 * @description
 * @date 2023/7/10 12:21
 */
@Service
public class MaterialOverviewServiceImpl extends ServiceImpl<MatertialOverviewMapper, MaterialOverview> implements MaterialOverviewService {

    @Autowired
    private MatertialOverviewMapper overviewMapper;

    @Override
    public IPage<MaterialOverview> getPage(int currentPage, int pageSize, MaterialOverview overview) {
        LambdaQueryWrapper<MaterialOverview> lqw = new LambdaQueryWrapper<>();
        lqw.like(Strings.isNotEmpty(overview.getCategory()),
                MaterialOverview::getCategory,overview.getCategory());
        IPage page = new Page(currentPage, pageSize);
        lqw.orderByDesc(MaterialOverview::getUpdateTime);
        overviewMapper.selectPage(page, lqw);
        return page;
    }
}
