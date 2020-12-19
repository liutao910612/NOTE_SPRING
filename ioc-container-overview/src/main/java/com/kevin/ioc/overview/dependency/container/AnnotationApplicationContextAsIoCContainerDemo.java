package com.kevin.ioc.overview.dependency.container;

import com.kevin.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * 注解{@link ApplicationContext}作为IOC容器
 *
 * @Author:Kevin
 * @Date:Created in 21:17 2020/12/1
 */
public class AnnotationApplicationContextAsIoCContainerDemo {
    public static void main(String[] args) {

        //创建beanFactory容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        //将当前类AnnotationApplicationContextAsIoCContainerDemo作为配置类（Configuration Class）
        applicationContext.register(AnnotationApplicationContextAsIoCContainerDemo.class);

        //启动应用上下文
        applicationContext.refresh();

        lookUpByType(applicationContext);

        //关闭应用上下文
        applicationContext.close();
    }

    @Bean
    public User initUser(){
        User user = new User();
        user.setId(1L);
        user.setName("Kevin");
        return user;
    }

    private static void lookUpByType(BeanFactory beanFactory) {
        System.out.println("通过类型查找 ：" + beanFactory.getBean(User.class));
    }
}
