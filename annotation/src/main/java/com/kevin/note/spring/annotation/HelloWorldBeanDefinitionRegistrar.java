package com.kevin.note.spring.annotation;

import org.springframework.beans.factory.annotation.AnnotatedGenericBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * "HelloWorld"模块{@link ImportBeanDefinitionRegistrar}
 * @Author:Kevin
 * @Date:Created in 21:45 2021/1/12
 */
public class HelloWorldBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar{

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata
            , BeanDefinitionRegistry registry) {

        AnnotatedGenericBeanDefinition beanDefinition = new AnnotatedGenericBeanDefinition(HelloWorldConfiguration.class);
        BeanDefinitionReaderUtils.registerWithGeneratedName(beanDefinition,registry);
    }
}
