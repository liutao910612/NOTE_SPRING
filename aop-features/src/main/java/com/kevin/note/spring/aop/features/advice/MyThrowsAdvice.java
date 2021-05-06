package com.kevin.note.spring.aop.features.advice;

import org.springframework.aop.ThrowsAdvice;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @Author:Kevin
 * @Date:Created in 21:16 2021/5/6
 */
public class MyThrowsAdvice implements ThrowsAdvice {
    public void afterThrowing(Exception e) {
        System.out.printf("Exception : %s\n"
                , e);
    }

    public void afterThrowing(Method method, Object[] args, Object target, Exception e) {
        System.out.printf("Method : %s,args : %s,target : %s,exception : %s\n"
                , method
                , Arrays.asList(args)
                , target
                , e);
    }
}
