package com.kevin.ioc.overview.dependency.lookup;

import com.kevin.base.annotation.Super;
import com.kevin.base.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

/**
 * 依赖查找实例
 * 1通过名称查找
 * 2通过类型查找
 *
 * @Author:Kevin
 * @Date:Created in 21:42 2020/11/12
 */
public class DependencyLookupDemo {
    public static void main(String[] args) {
        //配置xml配置文件
        //启动Spring应用上下文
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("META-INF/dependency-lookup-context.xml");
        lookUpInRealTime(beanFactory);
        lookUpInLazy(beanFactory);
        lookUpByType(beanFactory);
        lookUpCollectionByType(beanFactory);
        lookUpCollectionByAnnotation(beanFactory);
    }

    private static void lookUpCollectionByAnnotation(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String,User> userMap = (Map) listableBeanFactory.getBeansWithAnnotation(Super.class);
            System.out.println("根据注解@Super查找集合对象 ：" +userMap);
        }
    }

    private static void lookUpCollectionByType(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String,User> userMap = listableBeanFactory.getBeansOfType(User.class);
            System.out.println("根据类型查找集合对象 ：" +userMap);
        }
    }

    private static void lookUpByType(BeanFactory beanFactory) {
        System.out.println("通过类型查找 ：" + beanFactory.getBean(User.class));
    }

    private static void lookUpInLazy(BeanFactory beanFactory) {
        ObjectFactory<User> objectFactory = (ObjectFactory<User>)beanFactory.getBean("objectFactory");
        User user = objectFactory.getObject();
        System.out.println("延迟查找 ："+user);
    }

    private static void lookUpInRealTime(BeanFactory beanFactory){
        User user = (User) beanFactory.getBean("user");
        System.out.println("实时查找 ："+user);
    }
}
