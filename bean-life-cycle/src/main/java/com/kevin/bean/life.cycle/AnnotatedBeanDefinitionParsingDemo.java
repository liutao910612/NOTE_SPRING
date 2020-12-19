package com.kevin.bean.life.cycle;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotatedBeanDefinitionReader;

/**
 * 注解BeanDefinition元信息解析示例
 *
 * @Author:Kevin
 * @Date:Created in 21:42 2020/12/17
 */
public class AnnotatedBeanDefinitionParsingDemo {

    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        //基于Java注解的AnnotatedBeanDefinitionReader的实现
        AnnotatedBeanDefinitionReader beanDefinitionReader = new AnnotatedBeanDefinitionReader(beanFactory);
        int beanDefinitionCountBefore = beanFactory.getBeanDefinitionCount();

        //注册当前类（非@Component class）
        beanDefinitionReader.register(AnnotatedBeanDefinitionParsingDemo.class);
        int beanDefinitionCountAfter = beanFactory.getBeanDefinitionCount();
        System.out.println("bean number : " + (beanDefinitionCountAfter-beanDefinitionCountBefore));

        //普通 Class作为Component注册到Spring IOC容器后，通过bean名称为annotatedBeanDefinitionParsingDemo
        //Bean名称生成来自于BeanNameGenerator,注解实现AnnotatedBeanNameGenerator
        System.out.println(beanFactory.getBean("annotatedBeanDefinitionParsingDemo",AnnotatedBeanDefinitionParsingDemo.class));
    }

    @Override
    public String toString() {
        return "annotatedBeanDefinitionParsingDemo";
    }
}
