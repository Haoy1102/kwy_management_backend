package com.kwy.management.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kwy.management.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author haoy
 * @description
 * @date 2023/7/5 14:06
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
