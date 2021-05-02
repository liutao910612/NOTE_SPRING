package com.kevin.note.spring.aop.features.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

/**
 * @Author:Kevin
 * @Date:Created in 5:48 2021/5/2
 */
@Aspect
public class AspectConfiguration {

    @Pointcut("execution(public * *(..))")  //匹配Join Point
    private void anyPublicMethod() {//方法名即Pointcut名
        System.out.println("@Pointcut at any public method.");
    }

    @Before("anyPublicMethod()")  //Join Point拦截动作
    public void beforeAnyPublicMethod() {
        System.out.println("@Before at any public method.");
    }

    @Around("anyPublicMethod()")  //Join Point拦截动作
    public Object aroundAnyPublicMethod(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("@Around at any public method.");
        return pjp.proceed();
    }

    @After("anyPublicMethod()")
    public void afterAnyPublicMethod() {
        System.out.println("@After at any public method.");
    }

    @AfterReturning("anyPublicMethod()")
    public void afterReturnAnyPublicMethod() {
        System.out.println("@AfterReturning at any public method.");
    }

    @AfterThrowing("anyPublicMethod()")
    public void afterThrowAnyPublicMethod() {
        System.out.println("@AfterThrowing at any public method.");
    }
}
