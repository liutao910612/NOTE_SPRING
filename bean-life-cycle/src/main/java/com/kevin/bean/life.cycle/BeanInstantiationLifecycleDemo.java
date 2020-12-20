package com.kevin.bean.life.cycle;

import com.kevin.ioc.overview.domain.SuperUser;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.util.ObjectUtils;

/**
 * bean实例化前阶段示例
 *
 * @Author:Kevin
 * @Date:Created in 21:42 2020/12/17
 */
public class BeanInstantiationLifecycleDemo {

    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        beanFactory.addBeanPostProcessor( new MyInstantiationAwareBeanPostProcessor());

        //基于Java注解的AnnotatedBeanDefinitionReader的实现
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        String location = "META-INF/dependency-lookup-context.xml";
        beanDefinitionReader.loadBeanDefinitions(location);
        System.out.println(beanFactory.getBean("user"));
        System.out.println(beanFactory.getBean("superUser"));
    }

    static class MyInstantiationAwareBeanPostProcessor implements InstantiationAwareBeanPostProcessor {

        @Override
        public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
            if(ObjectUtils.nullSafeEquals("superUser",beanName) && SuperUser.class.equals(beanClass)){

                //把XML配置完成的SuperUser bean覆盖掉
                return new SuperUser();
            }
            return null;  //返回为空的时候会继续创建bean
        }
    }
}
