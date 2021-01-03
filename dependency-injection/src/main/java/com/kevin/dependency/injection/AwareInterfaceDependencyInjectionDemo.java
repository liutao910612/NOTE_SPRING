package com.kevin.dependency.injection;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.Aware;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 基于{@link Aware}接口回调的依赖注入示例
 * @Author:Kevin
 * @Date:Created in 14:51 2020/12/6
 */
public class AwareInterfaceDependencyInjectionDemo implements BeanFactoryAware,ApplicationContextAware{

    private static BeanFactory beanFactory;

    private static ApplicationContext applicationContext;

    public static void main(String[] args) {
        //创建beanFactory容器
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        //注册Configuration Class(配置类) ->Spring Bean
        context.register(AwareInterfaceDependencyInjectionDemo.class);

        //启动Spring应用上下文
        context.refresh();
        System.out.println(beanFactory == context.getBeanFactory());
        System.out.println(applicationContext == context);

        //显示关闭Spring应用上下文
        context.close();

    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        AwareInterfaceDependencyInjectionDemo.beanFactory = beanFactory;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        AwareInterfaceDependencyInjectionDemo.applicationContext = applicationContext;
    }
}
