package com.kevin.dependency.source;

import com.kevin.ioc.overview.repository.UserRepository;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ResourceLoader;

import javax.annotation.PostConstruct;

/**
 * 依赖查找实例
 *
 * @Author:Kevin
 * @Date:Created in 21:42 2020/11/12
 */
public class DependencySourceDemo {

    @Autowired
    private BeanFactory beanFactory;

    @Autowired
    private ResourceLoader resourceLoader;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @PostConstruct
    public void initByInjection(){
        System.out.println("beanFactory == applicationContext :" + (beanFactory == applicationContext));
        System.out.println("beanFactory == applicationContext.getBeanFactory() :" + (beanFactory == applicationContext.getAutowireCapableBeanFactory()));
        System.out.println("resourceLoader == applicationContext :" + (resourceLoader == applicationContext));
        System.out.println("applicationEventPublisher == applicationContext :" + (applicationEventPublisher == applicationContext));
    }

    public static void main(String[] args) {
        //创建beanFactory容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        //注册Configuration Class(配置类)
        applicationContext.register(DependencySourceDemo.class);

        //启动Spring应用上下文
        applicationContext.refresh();

        //显示关闭Spring应用上下文
        applicationContext.close();
    }

}
