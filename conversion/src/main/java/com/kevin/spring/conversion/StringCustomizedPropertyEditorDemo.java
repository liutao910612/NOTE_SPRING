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

        //AbstractApplicationContext -> "conversion" ConversionService Bean
        // -> ConfigurableBeanFactory#setConversionService(ConversionService)
        // -> AbstractAutowireCapableBeanFactory.instantiateBean
        // -> AbstractBeanFactory#getConversionService ->
        // BeanDefinition -> BeanWrapper -> 属性转换（数据来源：PropertiesValues） ->
        //setPropertyValues(PropertyValues) -> TypeConverter#convertIfNecessary
        //TypeConverterDelegate#convertIfNecessary -> PropertyEditor or ConversionService

        User user = applicationContext.getBean(User.class);
        System.out.println(user);
        applicationContext.close();
    }
}
