package com.kevin.note.spring.aop.features;

import com.kevin.note.spring.aop.features.interceptor.EchoServiceMethodInterceptor;
import com.kevin.note.spring.aop.features.pointcut.EchoServiceMethodPointcut;
import com.kevin.note.spring.aop.features.pointcut.EchoServicePointcut;
import com.kevin.note.spring.aop.overview.DefaultEchoService;
import com.kevin.note.spring.aop.overview.EchoService;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.ComposablePointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;

/**
 * @Author:Kevin
 * @Date:Created in 17:07 2021/5/2
 */
public class RawPointcutDemo {
    public static void main(String[] args) {
        EchoServicePointcut pointcut = new EchoServicePointcut("echo", EchoService.class);

        EchoServiceMethodPointcut methodPointcut = EchoServiceMethodPointcut.INSTANCE;

        //组合实现
        ComposablePointcut composablePointcut = new ComposablePointcut();
        composablePointcut.union(methodPointcut);
        composablePointcut.intersection(pointcut.getClassFilter());
        composablePointcut.intersection(pointcut.getMethodMatcher());

        //将Pointcut适配成Advisor
        DefaultPointcutAdvisor defaultPointcutAdvisor = new DefaultPointcutAdvisor(composablePointcut, new EchoServiceMethodInterceptor());
        EchoService echoService = new DefaultEchoService();
        ProxyFactory proxyFactory = new ProxyFactory(echoService);

        //添加Advisor
        proxyFactory.addAdvisor(defaultPointcutAdvisor);
        proxyFactory.addAdvice(new EchoServiceMethodInterceptor());

        //获取代理对象
        EchoService proxy = (EchoService) proxyFactory.getProxy();
        System.out.println(proxy.echo("Hello,World"));
    }
}
