package com.kwy.management.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kwy.management.entity.Order;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author haoy
 * @description
 * @date 2023/7/8 17:05
 */
@Mapper
public interface OrderMapper extends BaseMapper<Order> {
}
