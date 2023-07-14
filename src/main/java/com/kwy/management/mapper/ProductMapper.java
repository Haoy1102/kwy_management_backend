package com.kwy.management.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kwy.management.entity.Product;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author haoy
 * @description
 * @date 2023/7/14 18:58
 */
@Mapper
public interface ProductMapper extends BaseMapper<Product> {
}
