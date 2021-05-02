package com.kevin.note.spring.aop.features;

import com.kevin.note.spring.aop.overview.EchoService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author:Kevin
 * @Date:Created in 6:18 2021/5/2
 */
public class ProxyFactoryBeanDemo {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("META-INF/spring-aop-context.xml");
        EchoService echoService = context.getBean("echoServiceProxyFactoryBean",EchoService.class);
        System.out.println(echoService.echo("Hello,World"));
        context.close();
    }
}
