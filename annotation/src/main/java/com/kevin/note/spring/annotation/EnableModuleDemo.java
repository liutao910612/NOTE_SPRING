package com.kevin.note.spring.annotation;

import com.kevin.base.utils.PrintUtil;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Enable模块驱动示例
 *
 * @Author:Kevin
 * @Date:Created in 21:29 2021/1/12
 */
@EnableHelloWorld
public class EnableModuleDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(EnableModuleDemo.class);
        context.refresh();
        String helloWorld = context.getBean("helloWorld",String.class);
        PrintUtil.print(helloWorld);
        context.close();
    }
}
