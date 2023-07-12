package com.kwy.management.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.kwy.management.comon.Code;
import com.kwy.management.comon.R;
import com.kwy.management.dto.CustomerDto;
import com.kwy.management.entity.Customer;
import com.kwy.management.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.jdbc.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import sun.rmi.runtime.Log;

import java.util.List;

/**
 * @author haoy
 * @description
 * @date 2023/7/7 11:26
 */
@Slf4j
@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    /**
     * @param customer
     * @return
     */
    @PostMapping
    public R<Boolean> save(@RequestBody Customer customer) {
        return customerService.save(customer) ?
                R.success("添加成功") :
                R.error("添加失败，请检查数据。", Code.SAVE_ERR);
    }

    /**
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public R<Boolean> delete(@PathVariable int id) {
        return customerService.removeById(id) ?
                R.success("删除成功") :
                R.error("删除失败,用户不存在,自动刷新。", Code.DELETE_ERR);
    }

    /**
     * @param customer
     * @return
     */
    @PutMapping
    public R<Boolean> update(@RequestBody Customer customer) {
        return customerService.updateById(customer) ?
                R.success("修改成功!") :
                R.error("修改失败!用户不存在，自动刷新页面", Code.UPDATE_ERR);
    }

    /**
     * 返回页面数据
     *
     * @param currentPage
     * @param pageSize
     * @return
     */
    @GetMapping("/{currentPage}/{pageSize}")
    public R<IPage<Customer>> getPage(@PathVariable int currentPage, @PathVariable int pageSize,Customer customer) {
        IPage<Customer> page= customerService.getPage(currentPage, pageSize,customer);;
        //如果当前页码值大于了总页码值，那么重新执行查询操作，使用最大页码值作为当前页码值
        if( currentPage > page.getPages()){
            page = customerService.getPage((int)page.getPages(), pageSize,customer);
        }
        return R.success(page);
    }

    @GetMapping()
    public R<List<Customer>> getAll(){
        List<Customer> customerList = customerService.list();
        return !customerList.isEmpty()?
                R.success(customerList):
                R.error("获取客户数据失败",Code.GET_ERR);
    }

    @GetMapping("/{id}")
    public R<CustomerDto> getDetails(@PathVariable Long id){
        CustomerDto customerDto = customerService.getDetails(id);
        return R.success(customerDto);
    }


}
