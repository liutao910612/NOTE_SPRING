package com.kevin.configuration.metadata;

import com.kevin.base.domain.User;
import com.kevin.base.domain.UserHolder;
import com.kevin.base.enums.City;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.MapPropertySource;

import java.util.HashMap;
import java.util.Map;

/**
 * 外部化配置示例
 * @Author:Kevin
 * @Date:Created in 16:52 2021/1/1
 */
@PropertySource("classpath:/META-INFO/config-user.properties")
public class PropertySourceDemo {

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

        //扩展Environment中的PropertySource
        //添加PropertySource操作必须在refresh方法之前

        Map<String,Object> propertiesSource = new HashMap<>();
        propertiesSource.put("user.name","ALVIN LIU");
        org.springframework.core.env.PropertySource propertySource = new MapPropertySource("first-property-source",propertiesSource);
        applicationContext.getEnvironment().getPropertySources().addFirst(propertySource);

        applicationContext.register(PropertySourceDemo.class);
        applicationContext.refresh();
        Map<String, User> map = applicationContext.getBeansOfType(User.class);
        map.forEach((k, v) -> System.out.printf("User bean name : %s , content : %s \n", k, v));
        System.out.println(applicationContext.getEnvironment().getPropertySources());
    }
}
