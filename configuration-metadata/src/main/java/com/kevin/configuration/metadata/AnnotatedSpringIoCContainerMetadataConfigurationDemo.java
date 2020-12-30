package com.kevin.configuration.metadata;

import com.kevin.base.domain.User;
import com.kevin.base.domain.UserHolder;
import com.kevin.base.enums.City;
import com.kevin.configuration.component.Contract;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.*;

import java.util.Map;

/**
 * 基于Java注解的Spring IoC 容器元信息配置Demo
 *
 * @Author:Kevin
 * @Date:Created in 21:04 2020/12/28
 */

//将当前类作为Configuration Class
@ImportResource("classpath:/META-INFO/bean-context.xml")
@Import(UserHolder.class)
@PropertySource("classpath:/META-INFO/config-user.properties")
public class AnnotatedSpringIoCContainerMetadataConfigurationDemo {

    @Bean
    public User configUser(
            @Value("${user.id}") Long id,
            @Value("${user.name}") String name,
            @Value("${user.city}") City city
    ) {
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setCity(city);
        return user;
    }


    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(AnnotatedSpringIoCContainerMetadataConfigurationDemo.class);
        applicationContext.refresh();
        System.out.println(applicationContext.getBean(UserHolder.class));
        Map<String, User> map = applicationContext.getBeansOfType(User.class);
        map.forEach((k, v) -> {
            System.out.printf("User bean name : %s , content : %s \n", k, v);
        });
    }
}
