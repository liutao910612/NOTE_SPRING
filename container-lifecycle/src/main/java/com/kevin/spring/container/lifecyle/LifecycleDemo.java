package com.kevin.spring.container.lifecyle;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.Lifecycle;
import org.springframework.context.support.GenericApplicationContext;

/**
 * Customized {@link Lifecycle} demo
 * @Author:Kevin
 * @Date:Created in 20:46 2021/1/25
 */
public class LifecycleDemo {
    public static void main(String[] args) {
        GenericApplicationContext context = new GenericApplicationContext();
        context.registerBeanDefinition("myLifecycle", BeanDefinitionBuilder.genericBeanDefinition(MyLifecycle.class).getBeanDefinition());
        context.refresh();
        context.start();
        context.stop();
        context.close();
    }
}
