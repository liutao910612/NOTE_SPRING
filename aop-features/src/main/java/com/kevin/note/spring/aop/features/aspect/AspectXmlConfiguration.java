package com.kevin.note.spring.aop.features.aspect;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 * @Author:Kevin
 * @Date:Created in 5:48 2021/5/2
 */
public class AspectXmlConfiguration {

    public void beforeAnyPublicMethod(){
        System.out.println("@Before at any public method.");
    }

    public Object aroundAnyPublicMethod(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("@Around at any public method.");
        return pjp.proceed();
    }
}
