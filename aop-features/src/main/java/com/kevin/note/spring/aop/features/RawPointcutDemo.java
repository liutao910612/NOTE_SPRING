package com.kevin.note.spring.aop.features;

import com.kevin.note.spring.aop.features.interceptor.EchoServiceMethodInterceptor;
import com.kevin.note.spring.aop.features.pointcut.EchoServicePointcut;
import com.kevin.note.spring.aop.overview.DefaultEchoService;
import com.kevin.note.spring.aop.overview.EchoService;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;

/**
 * @Author:Kevin
 * @Date:Created in 17:07 2021/5/2
 */
public class RawPointcutDemo {
    public static void main(String[] args) {
        EchoServicePointcut pointcut = new EchoServicePointcut("echo", EchoService.class);

        //将Pointcut适配成Advisor
        DefaultPointcutAdvisor defaultPointcutAdvisor = new DefaultPointcutAdvisor(pointcut, new EchoServiceMethodInterceptor());
        EchoService echoService = new DefaultEchoService();
        ProxyFactory proxyFactory = new ProxyFactory(echoService);
        proxyFactory.addAdvisor(defaultPointcutAdvisor);
        proxyFactory.addAdvice(new EchoServiceMethodInterceptor());
        EchoService proxy = (EchoService) proxyFactory.getProxy();
        System.out.println(proxy.echo("Hello,World"));
    }
}
