package com.kevin.note.spring.aop.features.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;

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

    public void afterAnyPublicMethod() {
        System.out.println("@After at any public method.");
    }

    public void afterReturnAnyPublicMethod() {
        System.out.println("@AfterReturning at any public method.");
    }

    public void afterThrowAnyPublicMethod() {
        System.out.println("@AfterThrowing at any public method.");
    }
}
