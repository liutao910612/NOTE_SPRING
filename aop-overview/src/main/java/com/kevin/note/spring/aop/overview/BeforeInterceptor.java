package com.kevin.note.spring.aop.overview;

import java.lang.reflect.Method;

/**
 * 前置拦截器
 *
 * @Author:Kevin
 * @Date:Created in 21:09 2021/4/29
 */
public interface BeforeInterceptor {

    /**
     * 前置拦截
     *
     * @param proxy
     * @param method
     * @param args
     * @return
     */
    Object before(Object proxy, Method method, Object[] args);
}
