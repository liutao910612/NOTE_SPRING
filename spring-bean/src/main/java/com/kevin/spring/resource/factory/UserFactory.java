package com.kevin.spring.resource.factory;

import com.kevin.base.domain.User;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;

/**
 * @Author:Kevin
 * @Date:Created in 21:50 2020/12/2
 */
public class UserFactory implements InitializingBean{
    public User createUser() {
        User user = new User();
        user.setId(1L);
        user.setName("Kevin");
        return user;
    }

    @PostConstruct //1 基于@PostConstruct实现初始化
    public void init(){
        System.out.println("@PostConstruct:UserFactory初始化中...");
    }

    public void initUserFactory(){
        System.out.println("自定义初始化方法 initUserFactory():UserFactory初始化中...");
    }

    public void afterPropertiesSet() throws Exception {
        System.out.println("InitializingBean#afterPropertiesSet():UserFactory初始化中...");
    }
}
