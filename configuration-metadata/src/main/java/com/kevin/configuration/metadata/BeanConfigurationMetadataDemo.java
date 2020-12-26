package com.kevin.configuration.metadata;

import com.kevin.base.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.util.ObjectUtils;

/**
 * Bean配置元信息示例
 *
 * @Author:Kevin
 * @Date:Created in 16:00 2020/12/26
 */
public class BeanConfigurationMetadataDemo {
    public static void main(String[] args) {
        //BeanDefinition的定义（声明）
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
        beanDefinitionBuilder.addPropertyValue("name","kevin");

        //获取AbstractBeanDefinition
        AbstractBeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();

        //附加属性（不影响Bean populate,initialize）
        beanDefinition.setAttribute("name","liutao");

        //当前BeanDefinition来自于何方（辅助作用），可以针对来自不同的类来做不同的定制
        beanDefinition.setSource(BeanConfigurationMetadataDemo.class);

        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        beanFactory.addBeanPostProcessor(new BeanPostProcessor() {
            @Override
            public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
                BeanDefinition bd = beanFactory.getBeanDefinition(beanName);
                if(ObjectUtils.nullSafeEquals("user",beanName)
                        && User.class.equals(bean.getClass())
                        && BeanConfigurationMetadataDemo.class.equals(beanDefinition.getSource())){ //通过source来判断

                    //属性（存储）上下文
                    String name = (String) bd.getAttribute("name");
                    User user = (User) bean;
                    user.setName(name);
                }
                return bean;
            }
        });

        //注册User的BeanDefinition
        beanFactory.registerBeanDefinition("user",beanDefinition);
        User user = beanFactory.getBean("user",User.class);
        System.out.println(user);
    }
}
