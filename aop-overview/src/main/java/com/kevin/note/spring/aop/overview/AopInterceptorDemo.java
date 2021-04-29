package com.kevin.note.spring.aop.overview;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * AOP拦截器示例
 *
 * @Author:Kevin
 * @Date:Created in 21:07 2021/4/29
 */
public class AopInterceptorDemo {
    public static void main(String[] args) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

        //前置 + 后置拦截器
        Object proxy = Proxy.newProxyInstance(classLoader, new Class[]{EchoService.class}, new InvocationHandler() {
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                if (method.getDeclaringClass().isAssignableFrom(EchoService.class)) {

                    //前置拦截器
                    BeforeInterceptor beforeInterceptor = new BeforeInterceptor() {
                        public Object before(Object proxy, Method method, Object[] args) {
                            return System.currentTimeMillis();
                        }
                    };

                    Long startTime = 0L;
                    Object result = null;
                    Long endTime = 0L;
                    try {
                        startTime = (Long) beforeInterceptor.before(proxy, method, args);
                        EchoService echoService = new DefaultEchoService();
                        result = echoService.echo(String.valueOf(args[0]));

                        //方法返回后置拦截器
                        AfterInterceptor afterInterceptor = new AfterInterceptor() {
                            public Object after(Object proxy, Method method, Object[] args, Object result) {
                                return System.currentTimeMillis();
                            }
                        };
                        endTime = (Long) afterInterceptor.after(proxy, method, args, result);
                    } finally {


                        //finally后置拦截器
                        FinallyInterceptor finallyInterceptor = new TimeFinallyInterceptor(startTime, endTime);
                        finallyInterceptor.finalize(proxy, method, args, result);
                    }

                    return result;
                }
                return null;
            }
        });

        EchoService echoService = (EchoService) proxy;
        echoService.echo("Hello,World");
    }
}

class TimeFinallyInterceptor implements FinallyInterceptor {

    private Long startTime;
    private Long endTime;

    public TimeFinallyInterceptor(Long startTime, Long endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Object finalize(Object proxy, Method method, Object[] args, Object result) {
        Long costTime = endTime - startTime;
        System.out.println("方法执行时间：" + costTime);
        return null;
    }
}