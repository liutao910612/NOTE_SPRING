package com.kevin.configuration.metadata;

import com.kevin.base.domain.User;
import com.kevin.base.domain.UserHolder;
import com.kevin.base.enums.City;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.EncodedResource;

import java.util.Map;

/**
 * Spring XML元素扩展示例
 *
 * @Author:Kevin
 * @Date:Created in 21:04 2020/12/28
 */

public class ExtensibleXmlAuthoringDemo {

    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        String location = "/META-INFO/user-context.xml";

        EncodedResource resource = new EncodedResource(new ClassPathResource(location),"UTF-8");
        int beanNumber = beanDefinitionReader.loadBeanDefinitions(resource);
        System.out.println("bean number : " + beanNumber);
        User user = beanFactory.getBean(User.class);
        System.out.println(user);
    }
}
