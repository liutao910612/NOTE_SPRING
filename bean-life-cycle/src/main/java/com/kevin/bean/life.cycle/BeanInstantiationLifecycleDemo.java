package com.kevin.bean.life.cycle;

import com.kevin.base.domain.UserHolder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * bean实例化前阶段示例
 *
 * @Author:Kevin
 * @Date:Created in 21:42 2020/12/17
 */
public class BeanInstantiationLifecycleDemo {

    public static void main(String[] args) {
        executeByBeanFactory();
        System.out.println("-------------------------------");
        executeByApplicationContext();
    }

    public static void executeByBeanFactory(){
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        beanFactory.addBeanPostProcessor(new MyInstantiationAwareBeanPostProcessor());
        beanFactory.addBeanPostProcessor(new MyDestructionAwareBeanPostProcessor());

        //基于Java注解的AnnotatedBeanDefinitionReader的实现
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        String location = "META-INF/dependency-lookup-context.xml";
        beanDefinitionReader.loadBeanDefinitions(location);

        //显示执行preInstantiateSingletons()
        //SmartInitializingSingleton通常在Spring ApplicationContext场景使用
        //preInstantiateSingletons将已注册的BeanDefinition初始化成Spring Bean
        beanFactory.preInstantiateSingletons();
        System.out.println(beanFactory.getBean("user"));
        System.out.println(beanFactory.getBean("superUser"));
        System.out.println(beanFactory.getBean("userHolder"));
        beanFactory.destroyBean("userHolder", beanFactory.getBean("userHolder"));
    }

    public static void executeByApplicationContext(){
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext();
        String location = "META-INF/dependency-lookup-context.xml";
        applicationContext.setConfigLocations(location);
        applicationContext.refresh();
        System.out.println(applicationContext.getBean("user"));
        System.out.println(applicationContext.getBean("superUser"));
        System.out.println(applicationContext.getBean("userHolder"));
        applicationContext.close();
    }
}

