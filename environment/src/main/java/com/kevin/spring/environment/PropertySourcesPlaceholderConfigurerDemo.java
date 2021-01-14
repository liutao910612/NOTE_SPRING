package com.kevin.spring.environment;

import com.kevin.base.domain.User;
import com.kevin.base.utils.PrintUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/**
 * {@link PropertySourcesPlaceholderConfigurer} demo
 *
 * @Author:Kevin
 * @Date:Created in 22:07 2021/1/12
 */
@Configuration
public class PropertySourcesPlaceholderConfigurerDemo {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("META-INF/bean-context.xml");
        context.refresh();
        User user = context.getBean("user", User.class);
        PrintUtil.print(user);
        context.close();
    }
}
