package com.kevin.dependency.lookup;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 层次性以来查找demo
 * @Author:Kevin
 * @Date:Created in 14:51 2020/12/6
 */
public class HierarchicalDependencyLookupDemo {
    public static void main(String[] args) {
        //创建beanFactory容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        //注册Configuration Class（配置类）
        applicationContext.register(ObjectProviderDemo.class);

        //启动应用上下文
        applicationContext.refresh();

        //1.获取HierarchicalBeanFactory <- ConfigurableBeanFactory <- ConfigurableListableBeanFactory
        ConfigurableListableBeanFactory beanFactory = applicationContext.getBeanFactory();
        System.out.println("当前BeanFactory的parent BeanFactory : "+beanFactory.getParentBeanFactory());

        //2.设置parent BeanFactory
        BeanFactory parentBeanFactory = new ClassPathXmlApplicationContext("META-INF/dependency-lookup-context.xml");
        beanFactory.setParentBeanFactory(parentBeanFactory);
        System.out.println("当前BeanFactory的parent BeanFactory : "+beanFactory.getParentBeanFactory());
        //关闭应用上下文
        applicationContext.close();
    }
}
