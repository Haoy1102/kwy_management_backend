package com.kwy.management.comon;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author haoy
 * @description MP的自动填充功能
 * @date 2023/7/9 12:34
 */
@Component
@Slf4j
public class MyMetaObjectHandler implements MetaObjectHandler {
    /**
     * 插入时自动填充
     * @param metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("公共字段自动填充[insert]...");
        log.info(metaObject.toString());
        metaObject.setValue("createTime", LocalDateTime.now());
        metaObject.setValue("updateTime", LocalDateTime.now());
//        Long currentId = BaseContext.getCurrentId();
        String currentUserName=BaseContext.getCurrentUserName();
        metaObject.setValue("createUser", currentUserName);
        metaObject.setValue("updateUser", currentUserName);
    }

    /**
     * 更新时自动填充
     * @param metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("公共字段自动填充[update]...");
        log.info(metaObject.toString());
        long id = Thread.currentThread().getId();
        log.info("当前线程：{}",id);

        Long currentId = BaseContext.getCurrentId();
        String currentUserName=BaseContext.getCurrentUserName();

        metaObject.setValue("updateUser", currentUserName);
        metaObject.setValue("updateTime", LocalDateTime.now());
    }
}
