package com.kevin.spring.resource.definition;

import com.kevin.base.domain.User;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.GenericBeanDefinition;

/**
 * @Author:Kevin
 * @Date:Created in 21:51 2020/12/1
 */
public class BeanDefinitionCreationDemo {
    public static void main(String[] args) {
        //1通过BeanDefinitionBuilder构建
        BeanDefinitionBuilder  beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(User.class);

        //通过属性设置
        beanDefinitionBuilder.addPropertyValue("id",1);
        beanDefinitionBuilder.addPropertyValue("name","kevin");

        //获取BeanDefinition实例
        BeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();
        //BeanDefinition并非bean的最终状态，可以自定义修改

        //2通过AbstractBeanDefinition以及派生类
        GenericBeanDefinition genericBeanDefinition = new GenericBeanDefinition();

        //设置bean类型
        genericBeanDefinition.setBeanClass(User.class);

        MutablePropertyValues propertyValues = new MutablePropertyValues();
        propertyValues.addPropertyValue("id",1);
        propertyValues.addPropertyValue("name","kevin");
        genericBeanDefinition.setPropertyValues(propertyValues);
    }
}
