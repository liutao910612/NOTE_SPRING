package com.kevin.bean.definition;

import com.kevin.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author:Kevin
 * @Date:Created in 21:51 2020/12/1
 */
public class BeanAliasDemo {
    public static void main(String[] args) {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("META-INF/dependency-definitions-context.xml");

        //通过别名获取曾用名“user”的bean
        User user = beanFactory.getBean("user",User.class);
        User userAlias = beanFactory.getBean("userAlias",User.class);
        System.out.println(user==userAlias);

    }
}
