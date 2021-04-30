package com.kevin.note.spring.aop.overview;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * CGLIB动态代理示例
 *
 * @Author:Kevin
 * @Date:Created in 7:03 2021/4/30
 */
public class CglibDynamicProxyDemo {

    public static void main(final String[] args) {
        Enhancer enhancer = new Enhancer();

        //指定super class = DefaultEchoService.class
        Class<?> superClass = DefaultEchoService.class;
        enhancer.setSuperclass(superClass);

        //指定拦截接口
        enhancer.setInterfaces(new Class[]{EchoService.class});

        enhancer.setCallback(new MethodInterceptor() {
            public Object intercept(Object source, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
                long startTime = System.currentTimeMillis();

                //source -> CGLIB子类
                //目标类DefaultEchoService
                String result = (String) methodProxy.invokeSuper(source,args);
                long costTime = System.currentTimeMillis() - startTime;
                System.out.println("echo方法执行时间：" + costTime + "ms.");
                return result;
            }
        });

        //创建代理对象
        EchoService echoService = (EchoService) enhancer.create();
        echoService.echo("Hello,World");
    }
}
