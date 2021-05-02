package com.kevin.note.spring.aop.features;

import com.kevin.note.spring.aop.features.aspect.AspectConfiguration;
import com.kevin.note.spring.aop.overview.EchoService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 基于XML配置Pointcut 示例
 *
 * @Author:Kevin
 * @Date:Created in 16:19 2021/5/2
 */
@Configuration
@EnableAspectJAutoProxy  //激活Aspect 注解自动代理
public class AspectJSchemaBasedPointcutDemo {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("META-INF/spring-aop-context.xml");
        context.refresh();
        EchoService echoService = context.getBean("echoService",EchoService.class);
        System.out.println(echoService.echo("Hello,World"));
        context.close();
    }

}
