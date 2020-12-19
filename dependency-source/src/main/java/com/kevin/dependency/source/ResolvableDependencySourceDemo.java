package com.kevin.dependency.source;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.annotation.PostConstruct;

/**
 * ResolvableDependency作为依赖来源
 *
 * @Author:Kevin
 * @Date:Created in 21:42 2020/11/12
 */
public class ResolvableDependencySourceDemo {

    @Autowired
    private String value;

    @PostConstruct
    public void init(){
        System.out.println(value);
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        AutowireCapableBeanFactory beanFactory = applicationContext.getBeanFactory();
        if(beanFactory instanceof ConfigurableListableBeanFactory){
            ConfigurableListableBeanFactory configurableListableBeanFactory = ConfigurableListableBeanFactory.class.cast(beanFactory);
            configurableListableBeanFactory.registerResolvableDependency(String.class,"Hello ,world");
        }

        applicationContext.register(ResolvableDependencySourceDemo.class);

        applicationContext.refresh();

        applicationContext.close();
    }

}
