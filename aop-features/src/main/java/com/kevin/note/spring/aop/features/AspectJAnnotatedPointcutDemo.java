package com.kevin.note.spring.aop.features;

import com.kevin.note.spring.aop.features.aspect.AspectConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Pointcut 示例
 *
 * @Author:Kevin
 * @Date:Created in 16:19 2021/5/2
 */
@Configuration
@EnableAspectJAutoProxy  //激活Aspect 注解自动代理
public class AspectJAnnotatedPointcutDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(AspectJAnnotatedPointcutDemo.class, AspectConfiguration.class);
        context.refresh();
        AspectJAnnotatedPointcutDemo aspectJAnnotatedPointcutDemo = new AspectJAnnotatedPointcutDemo();
        aspectJAnnotatedPointcutDemo.execute();
        context.close();
    }

    public void execute(){
        System.out.println("execute()...");
    }
}
