package com.kevin.configuration.metadata;

import com.kevin.base.domain.User;
import org.springframework.beans.factory.support.BeanDefinitionReader;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;

/**
 * {@link PropertiesBeanDefinitionReader} 示例
 *
 * @Author:Kevin
 * @Date:Created in 16:36 2020/12/27
 */
public class PropertiesBeanDefinitionReaderDemo {
    public static void main(String[] args) {

        //创建IoC底层容器
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        //创建面向Properties资源的BeanDefinitionReader示例
        BeanDefinitionReader beanDefinitionReader = new PropertiesBeanDefinitionReader(beanFactory);
        int beanDefinitionsCount = beanDefinitionReader.loadBeanDefinitions("META-INFO/user.properties");
        System.out.println(String.format("Has loaded %d BeanDefinition\n",beanDefinitionsCount));
        User user =beanFactory.getBean(User.class);
    }
}
