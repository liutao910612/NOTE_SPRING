package com.kevin.spring.resource.scope;

import com.kevin.base.domain.User;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

/**
 * Bean的作用域示例
 *
 * @Author:Kevin
 * @Date:Created in 21:42 2020/11/12
 */
public class DependencySourceDemo {

    @Bean
    //默认scope就是singleton
    public static User singletonUser(){
         return buildUser();
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public static User prototypeUser(){
        return buildUser();
    }

    private static User buildUser(){
        User user = new User();
        user.setId(System.nanoTime());
        return user;
    }


    public static void main(String[] args) {
        //创建beanFactory容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        //注册Configuration Class(配置类)
        applicationContext.register(DependencySourceDemo.class);

        //启动Spring应用上下文
        applicationContext.refresh();

        scopedBeanByLookup(applicationContext);

        //显示关闭Spring应用上下文
        applicationContext.close();
    }

    private static void scopedBeanByLookup(AnnotationConfigApplicationContext applicationContext) {
        System.out.println("singleUser : " + applicationContext.getBean("singletonUser",User.class));
        System.out.println("protoTypeUser1 : " + applicationContext.getBean("prototypeUser",User.class));
        System.out.println("protoTypeUser2 : " + applicationContext.getBean("prototypeUser",User.class));
    }

}
