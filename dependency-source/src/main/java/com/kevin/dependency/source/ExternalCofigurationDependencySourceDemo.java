package com.kevin.dependency.source;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.annotation.PostConstruct;

/**
 * ResolvableDependency作为依赖来源
 *
 * @Author:Kevin
 * @Date:Created in 21:42 2020/11/12
 */
@PropertySource("META-INFO/default.properties")
@Configuration
public class ExternalCofigurationDependencySourceDemo {

    @Value("${user.id}")
    private Integer id;

    @Value("${user.name}")
    private String name;

    @PostConstruct
    public void init(){
        System.out.println(id);
        System.out.println(name);
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        applicationContext.register(ExternalCofigurationDependencySourceDemo.class);

        applicationContext.refresh();

        applicationContext.close();
    }

}
