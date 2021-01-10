package com.kevin.spring.event;

import com.kevin.base.utils.PrintUtil;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * {@link ApplicationListener}示例
 *
 * @Author:Kevin
 * @Date:Created in 14:39 2021/1/10
 */
@EnableAsync
public class ApplicationListenerDemo implements ApplicationEventPublisherAware{
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(ApplicationListenerDemo.class);

        //方法一：基于Spring接口：向Spring应用上下文注册事件
        //a 方法：基于ConfigurableApplicationContext API 实现
        applicationContext.addApplicationListener(event -> PrintUtil.print(Thread.currentThread().getName() + "ApplicationListener - Received Spring event : " + event));

        //b 方法：基于ApplicationListener 注册为Spring Bean
        applicationContext.register(MyApplicationListener.class);
        //方法二：基于Spring注解：org.springframework.context.event.EventListener
        applicationContext.refresh();
        applicationContext.start();
        applicationContext.close();
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        applicationEventPublisher.publishEvent(new ApplicationEvent("Hello world") {
        });
    }

    static class MyApplicationListener implements ApplicationListener {

        @Override
        public void onApplicationEvent(ApplicationEvent event) {
            PrintUtil.print(Thread.currentThread().getName() + "MyApplicationListener - Received Spring event : " + event);
        }
    }

    @EventListener
    @Order(2)
    public void onApplicationEvent2(ContextRefreshedEvent applicationEvent) {
        PrintUtil.print(Thread.currentThread().getName() + "@EventListener(onApplicationEvent2) - Received Spring ContextRefreshedEvent ");
    }

    @EventListener
    @Order(1)
    public void onApplicationEvent1(ContextRefreshedEvent applicationEvent) {
        PrintUtil.print(Thread.currentThread().getName() + "@EventListener(onApplicationEvent1) - Received Spring ContextRefreshedEvent ");
    }

    @EventListener
    @Async
    public void onApplicationEvent(ContextStartedEvent applicationEvent) {
        PrintUtil.print(Thread.currentThread().getName() + "异步，@EventListener - Received Spring ContextStartedEvent ");
    }

    @EventListener
    public void onApplicationEvent(ContextClosedEvent applicationEvent) {
        PrintUtil.print(Thread.currentThread().getName() + "@EventListener - Received Spring ContextClosedEvent ");
    }
}
