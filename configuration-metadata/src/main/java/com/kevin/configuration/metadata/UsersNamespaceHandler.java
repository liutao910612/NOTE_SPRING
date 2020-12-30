package com.kevin.configuration.metadata;

import org.springframework.beans.factory.xml.NamespaceHandler;
import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * "users.xsd"{@link NamespaceHandler}
 * @Author:Kevin
 * @Date:Created in 22:15 2020/12/30
 */
public class UsersNamespaceHandler extends NamespaceHandlerSupport {

    @Override
    public void init() {
        //将“user”元素注册对应的BeanDefinitionParser
        registerBeanDefinitionParser("user", new UserBeanDefinitionParser());
    }
}
