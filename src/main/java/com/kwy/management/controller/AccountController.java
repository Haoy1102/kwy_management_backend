package com.kwy.management.controller;

import com.kwy.management.comon.R;
import com.kwy.management.dto.AccountDto;
import com.kwy.management.entity.MaterialOverview;
import com.kwy.management.service.MaterialOverviewService;
import com.kwy.management.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author haoy
 * @description
 * @date 2023/7/12 12:56
 */
@Slf4j
@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private MaterialOverviewService materialOverviewService;

    @GetMapping("/{month}")
    public R<AccountDto> getAcountSimple(@PathVariable Long month){
        AccountDto accountDto = orderService.getAcountSimple(month);
        Double value = materialOverviewService.sumColumn("value");
        accountDto.setTotalAssetsMaterial(value);
        return R.success(accountDto);
    }
}
