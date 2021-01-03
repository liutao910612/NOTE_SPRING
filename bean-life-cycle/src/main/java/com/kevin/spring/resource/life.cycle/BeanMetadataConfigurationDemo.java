package com.kevin.spring.resource.life.cycle;

import com.kevin.base.domain.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.EncodedResource;

/**
 * Bean元信息配置示例
 *
 * @Author:Kevin
 * @Date:Created in 21:42 2020/12/17
 */
public class BeanMetadataConfigurationDemo {

    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        //实例化基于Properties资源BeanDefinitionReader
        PropertiesBeanDefinitionReader beanDefinitionReader = new PropertiesBeanDefinitionReader(beanFactory);
        String location = "/META-INFO/user.properties";

        //指定字符编码为UTF-8
        EncodedResource resource = new EncodedResource(new ClassPathResource(location),"UTF-8");
        int beanNumber = beanDefinitionReader.loadBeanDefinitions(resource);
        System.out.println("bean number : " + beanNumber);
        User user = beanFactory.getBean("user",User.class);
        System.out.println(user);
    }

}
