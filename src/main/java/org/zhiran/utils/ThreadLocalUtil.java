package org.zhiran.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * ThreadLocal 工具类
 */
@SuppressWarnings("all")
public class ThreadLocalUtil {
    //提供ThreadLocal对象,
    private static final ThreadLocal THREAD_LOCAL = new ThreadLocal();//初始化一个线程对象

    //根据键获取值
    public static <T> T get(){
        return (T) THREAD_LOCAL.get();
    }//拿出来一点数据
    //存储键值对
    public static void set(Object value){
        THREAD_LOCAL.set(value);
    }//传进去一点数据
    //清除ThreadLocal 防止内存泄漏
    public static void remove(){
        THREAD_LOCAL.remove();
    }//删除掉这个进程
}
