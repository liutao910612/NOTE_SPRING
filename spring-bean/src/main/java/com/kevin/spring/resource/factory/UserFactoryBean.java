package com.kevin.spring.resource.factory;

import com.kevin.base.domain.User;
import org.springframework.beans.factory.FactoryBean;

/**
 * @Author:Kevin
 * @Date:Created in 22:00 2020/12/2
 */
public class UserFactoryBean implements FactoryBean {
    public Object getObject() throws Exception {
        return User.createUser();
    }

    public Class<?> getObjectType() {
        return User.class;
    }
}
