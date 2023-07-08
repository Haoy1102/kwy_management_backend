package com.kwy.management.utils;

import com.kwy.management.comon.Code;
import com.kwy.management.comon.R;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author haoy
 * @description
 * @date 2023/7/8 12:26
 */
@RestControllerAdvice
public class ProjectExceptionAdvice {
    //拦截所有的异常信息
    @ExceptionHandler(Exception.class)
    public R<String> doException(Exception ex){
        //记录日志
        //通知运维
        //通知开发
        ex.printStackTrace();
        return R.error("服务器故障，请稍后再试！", Code.SYSTEM_ERR);
    }
}