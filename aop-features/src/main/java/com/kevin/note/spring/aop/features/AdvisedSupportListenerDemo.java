package com.kevin.note.spring.aop.features;

import com.kevin.note.spring.aop.features.interceptor.EchoServiceMethodInterceptor;
import com.kevin.note.spring.aop.overview.DefaultEchoService;
import com.kevin.note.spring.aop.overview.EchoService;
import org.aopalliance.aop.Advice;
import org.springframework.aop.framework.AdvisedSupport;
import org.springframework.aop.framework.AdvisedSupportListener;
import org.springframework.aop.framework.ProxyFactory;

/**
 * {@link AdvisedSupportListener}示例
 * @Author:Kevin
 * @Date:Created in 16:46 2021/5/9
 */
public class AdvisedSupportListenerDemo {
    public static void main(String[] args) {
        DefaultEchoService defaultEchoService = new DefaultEchoService();
        ProxyFactory proxyFactory = new ProxyFactory(defaultEchoService);
        proxyFactory.setTargetClass(DefaultEchoService.class);
        proxyFactory.addAdvice(new EchoServiceMethodInterceptor());
        proxyFactory.addListener(new AdvisedSupportListener() {
            public void activated(AdvisedSupport advised) {
                System.out.println("AOP配置对象 ："+advised + "已激活\n");
            }

            public void adviceChanged(AdvisedSupport advised) {
                System.out.println("AOP配置对象 ："+advised + "已变化\n");
            }
        });

        //激活事件触发createAopProxy() <- getProxy()
        EchoService proxy = (EchoService) proxyFactory.getProxy();
        proxyFactory.addAdvice(new Advice() {
        });
        System.out.println(proxy.echo("Hello,World"));

    }
}
