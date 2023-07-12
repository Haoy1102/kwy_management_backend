package com.kwy.management.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kwy.management.entity.User;
import com.kwy.management.mapper.UserMapper;
import com.kwy.management.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author haoy
 * @description
 * @date 2023/7/5 14:09
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
