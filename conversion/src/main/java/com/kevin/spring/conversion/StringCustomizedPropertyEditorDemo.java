package com.kevin.spring.conversion;

import com.kevin.base.domain.User;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.beans.PropertyEditor;

/**
 * Customized {@link PropertyEditor} demo
 * @Author:Kevin
 * @Date:Created in 14:24 2021/1/9
 */
public class StringCustomizedPropertyEditorDemo {
    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/META-INF/bean-context.xml");
        User user = applicationContext.getBean(User.class);
        System.out.println(user);
        applicationContext.close();
    }
}
