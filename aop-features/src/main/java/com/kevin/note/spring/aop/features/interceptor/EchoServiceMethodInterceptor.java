package com.kevin.note.spring.aop.features.interceptor;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.Method;

/**
 * @Author:Kevin
 * @Date:Created in 15:45 2021/5/2
 */
public class EchoServiceMethodInterceptor implements MethodInterceptor {
    public Object invoke(MethodInvocation invocation) throws Throwable {
        Method method = invocation.getMethod();
        System.out.println("拦截EchoService 的方法：" + method);
        return invocation.proceed();
    }
}
