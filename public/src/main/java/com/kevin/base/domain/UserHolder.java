package com.kevin.base.domain;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;

import javax.annotation.PreDestroy;

/**
 * @Author:Kevin
 * @Date:Created in 20:35 2020/12/22
 */
public class UserHolder implements BeanNameAware, BeanClassLoaderAware, BeanFactoryAware
        , EnvironmentAware,SmartInitializingSingleton,DisposableBean {
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
    @PreDestroy
    public void destroyBean(){
        this.setDesc("The user holder V10");
        System.out.println("destroyBean()="+desc);
    }

    public void destroy() throws Exception {
        this.setDesc("The user holder V11");
        System.out.println("destroy()="+desc);
    }

    public void selfDestroy() {
        this.setDesc("The user holder V12");
        System.out.println("selfDestroy()="+desc);
    }
}
