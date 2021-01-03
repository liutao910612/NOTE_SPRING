package com.kevin.spring.resource.life.cycle;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotatedBeanDefinitionReader;

/**
 * BeanDefinition合并示例
 *
 * @Author:Kevin
 * @Date:Created in 21:42 2020/12/17
 */
public class MergedBeanDefinitionDemo {

    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        //基于Java注解的AnnotatedBeanDefinitionReader的实现
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        String location = "META-INF/dependency-lookup-context.xml";
        beanDefinitionReader.loadBeanDefinitions(location);
        System.out.println(beanFactory.getBean("user"));
        System.out.println(beanFactory.getBean("superUser"));
    }
}
