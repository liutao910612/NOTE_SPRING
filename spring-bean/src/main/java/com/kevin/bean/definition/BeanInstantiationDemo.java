package com.kevin.bean.definition;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Bean实例化示例
 *
 * @Author:Kevin
 * @Date:Created in 21:46 2020/12/2
 */
public class BeanInstantiationDemo {
    public static void main(String[] args) {
        //配置XML配置文件
        //启动Spring应用上下文
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:META-INF/dependency-instantiation-context.xml");
        System.out.println(beanFactory.getBean("user-by-static-method"));
        System.out.println(beanFactory.getBean("user-by-instance-method"));
        System.out.println(beanFactory.getBean("user-by-factory-bean"));
    }
}
