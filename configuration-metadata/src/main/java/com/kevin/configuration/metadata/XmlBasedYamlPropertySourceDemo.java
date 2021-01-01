package com.kevin.configuration.metadata;

import com.kevin.base.domain.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * 基于XML的YAML外部化配置示例
 * @Author:Kevin
 * @Date:Created in 16:52 2021/1/1
 */
public class XmlBasedYamlPropertySourceDemo {


    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        String location = "META-INFO/yaml-property-source-context.xml";
        beanDefinitionReader.loadBeanDefinitions(location);
        System.out.println(beanFactory.getBean("yamlMap"));
    }
}
