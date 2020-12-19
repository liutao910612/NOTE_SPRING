package com.kevin.ioc.overview.repository;

import com.kevin.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ObjectFactory;

import java.util.Collection;

/**
 * @Author:Kevin
 * @Date:Created in 21:47 2020/11/25
 */
public class UserRepository {

    private Collection<User> users;  //定义bean

    private BeanFactory beanFactory;  //内建的非bean对象

    private ObjectFactory<User> objectFactory;

    public Collection<User> getUsers() {
        return users;
    }

    public void setUsers(Collection<User> users) {
        this.users = users;
    }

    public BeanFactory getBeanFactory() {
        return beanFactory;
    }

    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    public ObjectFactory<User> getObjectFactory() {
        return objectFactory;
    }

    public void setObjectFactory(ObjectFactory<User> objectFactory) {
        this.objectFactory = objectFactory;
    }
}
