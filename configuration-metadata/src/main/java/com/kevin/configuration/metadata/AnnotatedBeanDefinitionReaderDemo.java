package com.kevin.configuration.metadata;

import com.kevin.configuration.component.Contract;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.*;

/**
 * @Author:Kevin
 * @Date:Created in 21:04 2020/12/28
 */
@ComponentScan(basePackages = "com.kevin.configuration.component")
public class AnnotatedBeanDefinitionReaderDemo {
    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        beanFactory.registerBeanDefinition(AnnotationConfigUtils.CONFIGURATION_ANNOTATION_PROCESSOR_BEAN_NAME,new RootBeanDefinition(ConfigurationClassPostProcessor.class));
        AnnotatedBeanDefinitionReader annotatedBeanDefinitionReader = new AnnotatedBeanDefinitionReader(beanFactory);
        annotatedBeanDefinitionReader.register(AnnotatedBeanDefinitionReaderDemo.class);
        ConfigurationClassPostProcessor postProcessor = beanFactory.getBean(ConfigurationClassPostProcessor.class);

        //PostProcessor来处理@ComponentScn注解
        postProcessor.postProcessBeanFactory(beanFactory);
        System.out.println(beanFactory.getBean(Contract.class));
    }
}
