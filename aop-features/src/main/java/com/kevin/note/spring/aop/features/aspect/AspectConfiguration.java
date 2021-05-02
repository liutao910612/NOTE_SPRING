package com.kevin.note.spring.aop.features.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

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
    public void beforeAnyPublicMethod(){
        System.out.println("@Before at any public method.");
    }
}
