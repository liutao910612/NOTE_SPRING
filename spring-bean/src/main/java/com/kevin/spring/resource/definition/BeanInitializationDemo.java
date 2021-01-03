package com.kevin.spring.resource.definition;

import com.kevin.spring.resource.factory.UserFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Bean初始化demo
 *
 * @Author:Kevin
 * @Date:Created in 21:46 2020/12/2
 */
@Configuration
public class BeanInitializationDemo {
    public static void main(String[] args) {
        //创建BeanFactory容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        //注册Configuration Class(配置类)
        applicationContext.register(BeanInitializationDemo.class);

        //启动Spring应用上下文
        applicationContext.refresh();

        //依赖查找UserFactory
        UserFactory userFactory = applicationContext.getBean(UserFactory.class);

        //关闭Spring应用上下文
        applicationContext.close();
    }

    @Bean(initMethod = "initUserFactory")
    public UserFactory userFactory(){
        return new UserFactory();
    }
}
