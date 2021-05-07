package com.kevin.note.spring.annotation;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 激活“Hello World”模块
 * @Author:Kevin
 * @Date:Created in 21:29 2021/1/12
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented

//step2,通过@Import导入具体实现
//method1，通过Configuration Class实现
//@Import(HelloWorldConfiguration.class)

//method2，通过ImportSelector接口实现
//@Import(HelloWorldImportSelector.class)

//method3，通过ImportBeanDefinitionRegistrar
@Import(HelloWorldBeanDefinitionRegistrar.class)
public @interface EnableHelloWorld {  //step1,通过@Enable*命名
}
