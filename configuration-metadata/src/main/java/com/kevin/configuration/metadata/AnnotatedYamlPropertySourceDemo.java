package com.kevin.configuration.metadata;

import com.kevin.base.domain.User;
import com.kevin.base.domain.UserHolder;
import com.kevin.base.enums.City;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;

import java.util.Map;

/**
 * 基于java注解的YAML外部化配置示例
 * @Author:Kevin
 * @Date:Created in 16:52 2021/1/1
 */
@PropertySource(
        name = "yamlPropertySource"
        ,value = "classpath:/META-INFO/user.yaml"
        ,factory = YamlPropertySourceFactory.class
)
public class AnnotatedYamlPropertySourceDemo {
    @Bean
    public User user(
            @Value("${user.id}") Long id,
            @Value("${user.name}") String name
    ) {
        User user = new User();
        user.setId(id);
        user.setName(name);
        return user;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(AnnotatedYamlPropertySourceDemo.class);
        applicationContext.refresh();
        Map<String, User> map = applicationContext.getBeansOfType(User.class);
        map.forEach((k, v) -> {
            System.out.printf("User bean name : %s , content : %s \n", k, v);
        });
    }
}
