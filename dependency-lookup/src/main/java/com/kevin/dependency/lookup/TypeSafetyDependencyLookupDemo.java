package com.kevin.dependency.lookup;

import com.kevin.ioc.overview.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

/**
 * 类型安全依赖查找demo
 *
 * @Author:Kevin
 * @Date:Created in 14:11 2020/12/6
 */
public class TypeSafetyDependencyLookupDemo {

    public static void main(String[] args) {
        //创建beanFactory容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        //注册Configuration Class（配置类）
        applicationContext.register(TypeSafetyDependencyLookupDemo.class);

        //启动应用上下文
        applicationContext.refresh();

        displayObjectFactoryGetObject(applicationContext);
        displayBeanFactoryGetBean(applicationContext);
        displayObjectFactoryGetIfAvailable(applicationContext);
        //关闭应用上下文
        applicationContext.close();
    }

    /**
     * ObjectFactory#getIfAvailable方法的安全性
     *
     * @param applicationContext
     */
    private static void displayObjectFactoryGetIfAvailable(AnnotationConfigApplicationContext applicationContext){
        ObjectProvider<User> objectProvider = applicationContext.getBeanProvider(User.class);
        printBeansException(() -> objectProvider.getIfAvailable());
    }

    /**
     * ObjectFactory#getObject方法的安全性
     *
     * @param applicationContext
     */
    private static void displayObjectFactoryGetObject(AnnotationConfigApplicationContext applicationContext){
        ObjectProvider<User> objectProvider = applicationContext.getBeanProvider(User.class);
        printBeansException(() -> objectProvider.getObject());
    }

    /**
     * 演示BeanFactory#getBean方法的安全性
     *
     * @param beanFactory
     */
    private static void displayBeanFactoryGetBean(BeanFactory beanFactory) {
        printBeansException(() -> beanFactory.getBean(User.class));
    }

    private static void printBeansException(Runnable runnable) {
        try {
            runnable.run();
        } catch (BeansException e) {
            e.printStackTrace();
        }
    }
}
