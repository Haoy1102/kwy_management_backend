package com.kwy.management.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kwy.management.entity.Customer;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author haoy
 * @description
 * @date 2023/7/7 12:25
 */
@Mapper
public interface CustomerMapper extends BaseMapper<Customer> {
}
