package com.kevin.base.domain;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;

/**
 * @Author:Kevin
 * @Date:Created in 20:35 2020/12/22
 */
public class UserHolder implements BeanNameAware, BeanClassLoaderAware, BeanFactoryAware, EnvironmentAware,SmartInitializingSingleton {
    private User user;

    private String beanName;

    private ClassLoader classLoader;

    private BeanFactory beanFactory;

    private Environment environment;

    private String desc;

    public UserHolder(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "UserHolder{" +
                "user=" + user +
                ", beanName='" + beanName + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }

    public void setBeanClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    public void setBeanName(String name) {
        this.beanName = name;
    }

    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void afterSingletonsInstantiated() {
        this.desc = "The user holder V8";
        System.out.println("init()="+desc);
    }
}
