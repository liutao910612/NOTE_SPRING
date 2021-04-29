package com.kevin.note.spring.aop.overview;

import java.lang.reflect.Method;

/**
 * 最终执行后置拦截器
 *
 * @Author:Kevin
 * @Date:Created in 21:09 2021/4/29
 */
public interface FinallyInterceptor {

    /**
     * 最终执行
     *
     * @param proxy
     * @param method
     * @param args
     * @param result
     * @return
     */
    Object finalize(Object proxy, Method method, Object[] args, Object result);
}
