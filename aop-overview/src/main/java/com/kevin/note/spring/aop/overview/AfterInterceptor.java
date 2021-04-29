package com.kevin.note.spring.aop.overview;

import java.lang.reflect.Method;

/**
 * （方法返回）后置拦截器
 *
 * @Author:Kevin
 * @Date:Created in 21:09 2021/4/29
 */
public interface AfterInterceptor {

    /**
     * 后置拦截
     *
     * @param proxy
     * @param method
     * @param args
     * @param result
     * @return
     */
    Object after(Object proxy, Method method, Object[] args,Object result);
}
