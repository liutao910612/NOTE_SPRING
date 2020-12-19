package com.kevin.dependency.lookup;

import com.kevin.ioc.overview.domain.User;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.Iterator;

/**
 * 通过{@link ObjectProvider}进行依赖查找
 *
 * @Author:Kevin
 * @Date:Created in 14:11 2020/12/6
 */
public class ObjectProviderDemo {  //@Configuration不被需要

    public static void main(String[] args) {
        //创建beanFactory容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        //注册Configuration Class（配置类）
        applicationContext.register(ObjectProviderDemo.class);

        //启动应用上下文
        applicationContext.refresh();

        lookUpByObjectProvider(applicationContext);
        lookUpIfAvailable(applicationContext);
        lookUpByStringOps(applicationContext);

        //关闭应用上下文
        applicationContext.close();
    }

    private static void lookUpByStringOps(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<String> objectProvider = applicationContext.getBeanProvider(String.class);
        Iterable<String> stringIterable = objectProvider;
        for (String string:stringIterable) {
            System.out.println(string);
        }
    }

    @Bean
    @Primary
    public String helloWorld() {  //bean的名称即为方法名
        return "Hello ,world";
    }

    @Bean
    public String message() {  //bean的名称即为方法名
        return "Message";
    }

    private static void lookUpByObjectProvider(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<String> objectProvider = applicationContext.getBeanProvider(String.class);
        System.out.println(objectProvider.getObject());
    }

    private static void lookUpIfAvailable(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<User> objectProvider = applicationContext.getBeanProvider(User.class);
        System.out.println("当前User对象：" + objectProvider.getIfAvailable(User::createUser));
    }
}
