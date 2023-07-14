package com.kwy.management.comon;

/**
 * @author haoy
 * @description 封装ThreadLocal
 * @date 2022/12/1 17:01
 */
public class BaseContext {
    public static ThreadLocal<Long> threadLocal=new ThreadLocal<>();
    public static ThreadLocal<String> currentUserName = new ThreadLocal<>();

    public static void setCurrentId(Long id){
        threadLocal.set(id);
    }

    public static void setCurrentUserName(String name){
        currentUserName.set(name);
    }

    public static Long getCurrentId(){
        return threadLocal.get();
    }

    public static String getCurrentUserName(){
        return currentUserName.get();
    }
}
