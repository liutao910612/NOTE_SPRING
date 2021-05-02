package com.kevin.note.spring.aop.features;

import com.kevin.note.spring.aop.features.interceptor.EchoServiceMethodInterceptor;
import com.kevin.note.spring.aop.overview.DefaultEchoService;
import com.kevin.note.spring.aop.overview.EchoService;
import org.springframework.aop.framework.ProxyFactory;

/**
 * @Author:Kevin
 * @Date:Created in 16:07 2021/5/2
 */
public class ProxyFactoryDemo {
    public static void main(String[] args) {
        EchoService echoService = new DefaultEchoService();
        ProxyFactory proxyFactory = new ProxyFactory(echoService);
        proxyFactory.addAdvice(new EchoServiceMethodInterceptor());
        EchoService proxy = (EchoService) proxyFactory.getProxy();
        System.out.println(proxy.echo("Hello,World"));
    }
}
