package com.kevin.spring.environment;

import com.kevin.base.utils.PrintUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * {@link Value @Value}demo
 *
 * @Author:Kevin
 * @Date:Created in 22:07 2021/1/14
 */
public class ValueAnnotationDemo {

    @Value("${user.name}")
    public String userName;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(ValueAnnotationDemo.class);
        applicationContext.refresh();
        ValueAnnotationDemo valueAnnotationDemo = applicationContext.getBean(ValueAnnotationDemo.class);
        PrintUtil.print(valueAnnotationDemo.userName);
        applicationContext.close();
    }
}
