package com.kwy.management.comon;

import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 通用返回结果，服务端响应的数据最终都会封装成此对象
 * @param <T>
 */
@Data
public class R<T> implements Serializable {

//    private Integer code; //编码：1成功，0和其它数字为失败

    private String message; //错误信息

    private T data; //数据

    private Integer code;

    private Map map = new HashMap(); //动态数据
    //成功code返回0
    public static <T> R<T> success(T object) {
        R<T> r = new R<T>();
        r.data = object;
        r.code = Code.SUCCESS;
        return r;
    }
    public static <T> R<T> success(String message) {
        R<T> r = new R<T>();
        r.message=message;
        r.code = Code.SUCCESS;
        return r;
    }

    public static <T> R<T> success(T object,String message) {
        R<T> r = new R<T>();
        r.data = object;
        r.message=message;
        r.code = Code.SUCCESS;
        return r;
    }

    public static <T> R<T> success() {
        R<T> r = new R<T>();
        r.code = Code.SUCCESS;
        return r;
    }

    //code非0为不成功的状态码
    public static <T> R<T> error(String message,Integer code) {
        R r = new R();
        r.message = message;
        r.code = code;
        return r;
    }

    public R<T> add(String key, Object value) {
        this.map.put(key, value);
        return this;
    }

}
