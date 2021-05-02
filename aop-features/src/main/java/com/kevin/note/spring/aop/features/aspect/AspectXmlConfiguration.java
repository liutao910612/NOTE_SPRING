package com.kevin.note.spring.aop.features.aspect;

/**
 * @Author:Kevin
 * @Date:Created in 5:48 2021/5/2
 */
public class AspectXmlConfiguration {

    public void beforeAnyPublicMethod(){
        System.out.println("@Before at any public method.");
    }
}
