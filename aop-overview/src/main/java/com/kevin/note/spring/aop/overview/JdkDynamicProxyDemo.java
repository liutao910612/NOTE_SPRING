package com.kevin.note.spring.aop.overview;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * java动态代理示例
 *
 * @Author:Kevin
 * @Date:Created in 20:30 2021/4/29
 */
public class JdkDynamicProxyDemo {
    public static void main(String[] args) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        Object proxy = Proxy.newProxyInstance(classLoader, new Class[]{EchoService.class}, new InvocationHandler() {
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                if (method.getDeclaringClass().isAssignableFrom(EchoService.class)) {
                    EchoService echoService = new ProxyEchoService(new DefaultEchoService());
                    echoService.echo(String.valueOf(args[0]));
                }
                return null;
            }
        });

        EchoService echoService = (EchoService) proxy;
        echoService.echo("Hello,World");
    }
}
