package com.kevin.ioc.overview.dependency.injection;

import com.kevin.ioc.overview.repository.UserRepository;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 依赖查找实例
 * 1通过名称查找
 * 2通过类型查找
 *
 * @Author:Kevin
 * @Date:Created in 21:42 2020/11/12
 */
public class DependencyInjectionDemo {
    public static void main(String[] args) {
        //配置xml配置文件
        //启动Spring应用上下文
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("META-INF/dependency-injection-context.xml");

        UserRepository userRepository = beanFactory.getBean("userRepository",UserRepository.class);
        System.out.println(userRepository.getUsers());
        System.out.println(userRepository.getBeanFactory());
        System.out.println(userRepository.getBeanFactory() == beanFactory);

        ObjectFactory userFactory = userRepository.getObjectFactory();
        System.out.println(userFactory.getObject());
    }

}
